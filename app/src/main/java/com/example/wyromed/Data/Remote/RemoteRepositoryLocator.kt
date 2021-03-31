package com.example.wyromed.Data.Remote

import com.example.wyromed.Data.Connection.WyromedService

class RemoteRepositoryLocator(service: WyromedService) : IRemoteServiceLocator {

    companion object {
        @Volatile private var INSTANCE: RemoteRepositoryLocator? = null

        fun getInstance(service: WyromedService): RemoteRepositoryLocator {
            if(INSTANCE == null) {
                INSTANCE = RemoteRepositoryLocator(service)
            }
            return INSTANCE!!
        }

    }
}