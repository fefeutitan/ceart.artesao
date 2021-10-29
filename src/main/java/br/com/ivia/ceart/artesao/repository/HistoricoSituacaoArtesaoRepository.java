package br.com.ivia.ceart.artesao.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import br.com.ivia.ceart.artesao.model.HistoricoSituacaoArtesao;

@Repository
public interface HistoricoSituacaoArtesaoRepository
		extends PagingAndSortingRepository<HistoricoSituacaoArtesao, Integer> {
}
