package com.example.wyromed.Data.Repository

import com.example.wyromed.Data.Remote.RemoteRepositoryLocator

//class RepositoryLocator(remoteLocator: RemoteRepositoryLocator) : IRepositoryLocator {
//
//    companion object {
//        @Volatile private var INSTANCE: RepositoryLocator? = null
//
//        fun getInstance(remoteLocator: RemoteRepositoryLocator): RepositoryLocator {
//            if(INSTANCE == null) {
//                INSTANCE = RepositoryLocator(remoteLocator)
//            }
//            return INSTANCE!!
//        }
//
//    }
//
////    override val bookingRepository: BookingRepository by lazy {
////        BookingRepository(remoteLocator.bookingRemoteRepository)
////    }
//}