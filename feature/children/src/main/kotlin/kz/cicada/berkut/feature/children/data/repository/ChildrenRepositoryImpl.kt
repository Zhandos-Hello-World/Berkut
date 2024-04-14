package kz.cicada.berkut.feature.children.data.repository

import kotlinx.coroutines.withContext
import kz.cicada.berkut.feature.children.data.model.ChildrenResponse
import kz.cicada.berkut.feature.children.data.network.ChildrenApi
import kz.cicada.berkut.feature.children.domain.repository.ChildrenRepository
import kotlin.coroutines.CoroutineContext

class ChildrenRepositoryImpl(
    private val ioDispatcher: CoroutineContext,
    private val api: ChildrenApi,
) : ChildrenRepository {

    override suspend fun getChildren(parentId: Int): List<ChildrenResponse> {
        return withContext(ioDispatcher) {
            api.getChildren(parentId)
        }
    }
}