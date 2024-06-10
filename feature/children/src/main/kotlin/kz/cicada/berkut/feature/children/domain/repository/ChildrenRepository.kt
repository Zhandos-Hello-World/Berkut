package kz.cicada.berkut.feature.children.domain.repository

import kz.cicada.berkut.feature.children.data.model.ChildrenResponse

interface ChildrenRepository {

    suspend fun getChildren(parentId: Int): List<ChildrenResponse>

    suspend fun getChildPhoto(userId: Int): String
}