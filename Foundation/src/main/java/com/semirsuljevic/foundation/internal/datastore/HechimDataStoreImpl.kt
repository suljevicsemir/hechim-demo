package com.semirsuljevic.foundation.internal.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.dataStore
import com.semirsuljevic.foundation.api.datastore.HechimDataStore
import com.semirsuljevic.foundation.api.datastore.model.PermissionRequests
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class HechimDataStoreImpl @Inject constructor(
    private val context: Context
): HechimDataStore{
    override val Context.permissionRequestsDataStore: DataStore<PermissionRequests> by dataStore(
        fileName = "permission-requests.json",
        serializer = PermissionRequestsSerializer
    )

    override val permissionRequestsFlow: Flow<PermissionRequests> = context.permissionRequestsDataStore.data

    override suspend fun updatePermissionRequests(permissionRequests: PermissionRequests) {
        context.permissionRequestsDataStore.updateData {
            permissionRequests
        }
    }
}
