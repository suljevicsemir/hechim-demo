package com.semirsuljevic.foundation.api.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import com.semirsuljevic.foundation.api.datastore.model.PermissionRequests
import kotlinx.coroutines.flow.Flow

interface HechimDataStore {

    val Context.permissionRequestsDataStore: DataStore<PermissionRequests>
    val permissionRequestsFlow: Flow<PermissionRequests>

    suspend fun updatePermissionRequests(permissionRequests: PermissionRequests)
}
