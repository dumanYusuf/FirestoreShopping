package com.example.firestoreshopping.data.di

import com.example.firestoreshopping.data.repo.AuthRepoImpl
import com.example.firestoreshopping.data.repo.FirestoreRepoImpl
import com.example.firestoreshopping.domain.repo.AuthRepo
import com.example.firestoreshopping.domain.repo.FirestoreRepo
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {


    @Provides
    @Singleton
    fun providesAuth():FirebaseAuth=FirebaseAuth.getInstance()

    @Provides
    @Singleton
    fun providesFirestore():FirebaseFirestore=FirebaseFirestore.getInstance()

    @Provides
    @Singleton
    fun providesAuthRepo(auth: FirebaseAuth,firestore: FirebaseFirestore):AuthRepo{
        return AuthRepoImpl(auth,firestore)
    }

    @Provides
    @Singleton
    fun providesFirestoreRepo(firestore:FirebaseFirestore):FirestoreRepo{
        return FirestoreRepoImpl(firestore)
    }



}