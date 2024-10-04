package com.example.firestoreshopping.data.repo

import android.util.Log
import com.example.firestoreshopping.domain.model.Basket
import com.example.firestoreshopping.domain.model.Category
import com.example.firestoreshopping.domain.model.Favori
import com.example.firestoreshopping.domain.model.LocationUser
import com.example.firestoreshopping.domain.model.Products
import com.example.firestoreshopping.domain.model.Users
import com.example.firestoreshopping.domain.repo.FirestoreRepo
import com.example.firestoreshopping.util.Resource
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class FirestoreRepoImpl @Inject constructor(
    private val firestore:FirebaseFirestore,
    private val auth:FirebaseAuth
):FirestoreRepo {

    override suspend fun getCategory(): Flow<Resource<List<Category>>> = flow {
        try {
            val result = firestore.collection("Category").get().await()
            val category = result.documents.map {
                it.toObject(Category::class.java)!!.copy(categoryId = it.id)
            }
            emit(Resource.Success(category))
        } catch (e: Exception) {
            emit(Resource.Error("Error"))
            Log.e("error", "erroro:${e.localizedMessage}")
        }
    }

    override suspend fun getProducts(categoryId: String): Flow<Resource<List<Products>>> = flow {
        try {
            val result = firestore.collection("Category")
                .document(categoryId)
                .collection("Products").get().await()

            val products = result.documents.mapNotNull {
                it.toObject(Products::class.java)?.copy(productId = it.id)
            }
            emit(Resource.Success(products))
        } catch (e: Exception) {
            emit(Resource.Error("Error: ${e.localizedMessage}"))
            Log.e("error", "error: ${e.localizedMessage}")
        }
    }

    override suspend fun productSearch(
        search: String,
        categoryId: String
    ): Flow<Resource<List<Products>>> = flow {
        try {
            emit(Resource.Loading())
            val productDocRef = firestore.collection("Category").document(categoryId)
                .collection("Products").get().await()

            val searchList = productDocRef.documents.mapNotNull { document ->
                document.toObject(Products::class.java)?.takeIf { product ->
                    product.productName.contains(search, ignoreCase = true)
                }
            }
            emit(Resource.Success(searchList))
        } catch (e: Exception) {
            emit(Resource.Error("Error: ${e.message}"))
        }
    }

   override suspend fun addProductToFavori(products: Favori): Resource<Favori> {
       return try {
           val userId = auth.currentUser?.uid
           if (userId != null) {
               firestore.collection("Users").document(userId)
                   .collection("Favori").document(products.favoriId)
                   .set(products.toMap()).await()
               Resource.Success(products)
           } else {
               Resource.Error("User ID is null")
           }
       } catch (e: Exception) {
           Resource.Error("Error: ${e.message}")
       }
   }


    override suspend fun getProductToFavori(): Flow<Resource<List<Favori>>> = flow{
        try {
            val userId=auth.currentUser?.uid
            if (userId!=null){
                val favoriDocRef=firestore.collection("Users").document(userId)
                    .collection("Favori").get().await()
                val favoriList=favoriDocRef.documents.mapNotNull {
                    it.toObject(Favori::class.java)
                }
                emit(Resource.Success(favoriList))
            }
        }
        catch (e:Exception){
            emit(Resource.Error("Error"))
        }
    }

    override suspend fun deleteProducts(id: Favori): Resource<Favori> {
        return try {
            val userId = auth.currentUser?.uid
            if (userId != null) {
                firestore.collection("Users").document(userId)
                    .collection("Favori").document(id.favoriId).delete().await()
                Resource.Success(id)
            }

            else {
                Resource.Error("User Not Found")
            }
        } catch (e: Exception) {
            Resource.Error("Error: ${e.message}")
        }
    }

    override suspend fun addProductToBasket(product: Basket): Resource<Basket> {

      return  try {
            val userId=auth.currentUser?.uid
          if (userId!=null){
              firestore.collection("Users").document(userId)
                  .collection("Basket").document(product.basketId)
                  .set(product.toMap()).await()
              Resource.Success(product)
          }
          else{
              Resource.Error("USER NOT FOUND")
          }
        }
        catch (e:Exception){
            Resource.Error("Erorr")
        }

    }

    override suspend fun getProductToBasket(): Flow<Resource<List<Basket>>> = flow{

        try {
            val userId=auth.currentUser?.uid
            if (userId!=null){
               val result= firestore.collection("Users").document(userId)
                    .collection("Basket").get().await()

                val basketList=result.documents.mapNotNull {
                    it.toObject(Basket::class.java)
                }
                emit(Resource.Success(basketList))
            }
        }
        catch (e:Exception){
            emit(Resource.Error("Error message"))
        }

    }

    override suspend fun deleteBasketProduct(id: Basket): Resource<Basket> {
        return try {
            val userId = auth.currentUser?.uid
            if (userId != null) {
                firestore.collection("Users").document(userId)
                    .collection("Basket").document(id.basketId).delete().await()
                Resource.Success(id)
            }

            else {
                Resource.Error("User Not Found")
            }
        } catch (e: Exception) {
            Resource.Error("Error: ${e.message}")
        }
    }

    override suspend fun getUser(): Flow<Resource<List<Users>>> = flow {
        try {
            val currentUser = auth.currentUser?.uid
            if (currentUser != null) {
                val userDocRef = firestore.collection("Users").document(currentUser).get().await()
                val user = userDocRef.toObject(Users::class.java)
                if (user != null) {
                    emit(Resource.Success(listOf(user)))
                } else {
                    emit(Resource.Error("User not found"))
                }
            } else {
                emit(Resource.Error("Current user is null"))
            }
        } catch (e: Exception) {
            emit(Resource.Error("Error: ${e.localizedMessage}"))
        }
    }

    override suspend fun addLocation(location: LocationUser): Resource<LocationUser> {
        return try {
            val currentUser = auth.currentUser?.uid
            if (currentUser != null) {
                firestore.collection("Users").document(currentUser)
                    .collection("Location").document()
                    .set(location.toMap())
                    .await()
                Resource.Success(location)
            } else {
                Resource.Error("User Not Found")
            }
        } catch (e: Exception) {
            Resource.Error("Error: ${e.message}")
        }
    }

    override suspend fun getLocation(): Flow<Resource<List<LocationUser>>> = flow {
        try {
            val currentUser = auth.currentUser?.uid
            if (currentUser != null) {
                val locationSnapshot = firestore.collection("Users")
                    .document(currentUser).collection("Location").get().await()
                val locations = locationSnapshot.documents.mapNotNull { document ->
                    document.toObject(LocationUser::class.java)
                }
                emit(Resource.Success(locations))
            } else {
                emit(Resource.Error("User Not Found"))
            }
        } catch (e: Exception) {
            emit(Resource.Error("Error: ${e.message}"))
        }
    }


}
