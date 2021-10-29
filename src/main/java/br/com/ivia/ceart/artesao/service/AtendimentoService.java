package br.com.ivia.ceart.artesao.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import br.com.ivia.ceart.artesao.model.Atendimento;
import br.com.ivia.ceart.artesao.repository.AtendimentoRepository;
import br.com.ivia.ceart.artesao.to.AtendimentoTO;
import br.com.ivia.ceart.artesao.to.MessageTO;
import br.com.ivia.ceart.artesao.util.enums.SituacaoAtual;
import br.com.ivia.ceart.artesao.util.exception.ArtesaoException;
import br.com.ivia.ceart.artesao.util.specification.AtendimentoSpecification;

@Service
public class AtendimentoService {

    @Autowired
    private AtendimentoRepository repository;
     
    public MessageTO findByProtocolo(AtendimentoTO atendimento) {
    	AtendimentoSpecification specification = new AtendimentoSpecification(atendimento);
        PageRequest pageRequest = PageRequest.of(atendimento.getPageNumber() - 1, atendimento.getPageSize(), new Sort(atendimento.getDirection(), atendimento.getBy()));
        Page<Atendimento> page = repository.findAll(specification, pageRequest);
        List<Atendimento> atendimentos = page.getContent();
        
        for (Atendimento at : atendimentos) {
        	if (at.getSituacao().equals(SituacaoAtual.ENCERRADO)) {
        		throw new ArtesaoException("Não é possivel realizar o cadastro do artesão, pois o atendimento está encerrado!");
        	}
        }
        return new MessageTO("Atendimentos encontrados", true, page.getTotalPages(), atendimentos);
    }
    
    public Atendimento findById(Integer id) {
        Optional<Atendimento> atendimento = repository.findById(id);
        if (!atendimento.isPresent()) {
            throw new ArtesaoException("Erro ao procurar o atendimento.");
        }
        return atendimento.get();
    }
}
