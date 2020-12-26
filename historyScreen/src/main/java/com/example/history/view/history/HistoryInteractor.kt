package com.example.history.view.history

import com.example.core.viewmodel.Interactor
import com.example.model.data.AppState
import com.example.model.data.dto.SearchResultDto
import com.example.repository.Repository
import com.example.repository.RepositoryLocal
import com.example.translator.utils.mapSearchResultToResult

class HistoryInteractor(
        private val repositoryRemote: Repository<List<SearchResultDto>>,
        private val repositoryLocal: RepositoryLocal<List<SearchResultDto>>
) : Interactor<AppState> {

    override suspend fun getData(word: String, fromRemoteSource: Boolean): AppState {
        return AppState.Success(
                mapSearchResultToResult(
                        if (fromRemoteSource) {
                            repositoryRemote
                        } else {
                            repositoryLocal
                        }.getData(word)
                )
        )
    }
}
