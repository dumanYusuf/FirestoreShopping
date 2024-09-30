package com.example.firestoreshopping.data.repo


import com.example.firestoreshopping.domain.model.Users
import com.example.firestoreshopping.domain.repo.AuthRepo
import com.example.firestoreshopping.util.Resource
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class AuthRepoImpl @Inject constructor(
    private val auth:FirebaseAuth,
    private val firestore: FirebaseFirestore
):AuthRepo {


    override suspend fun register(users: Users, password: String): Resource<Users> {
        return try {
            val result = auth.createUserWithEmailAndPassword(users.userMail, password).await()
            val firebaseUser = result.user

            firebaseUser?.let {
                val newUser = Users(
                    userId = it.uid,
                    userMail = it.email.toString(),
                    userName = users.userName,
                    userLastName = users.userLastName,
                    userProfilUrl = "https://media.istockphoto.com/id/1332100919/tr/vekt%C3%B6r/man-icon-black-icon-person-symbol.jpg?s=612x612&w=0&k=20&c=lVmBCgzh7mw_UbIHKvFtpi7W8J21mEdrNQsfOgn0PxA=" // Profil resmi için varsayılan bir URL
                )

                firestore.collection("Users").document(newUser.userId).set(newUser.toMap()).await()

                Resource.Success(newUser)
            } ?: Resource.Error("user not found:")

        } catch (e: Exception) {
            Resource.Error("Hata: ${e.message}")
        }
    }

    override suspend fun login(email: String, password: String): Resource<Users> {
        return try {
            val result = auth.signInWithEmailAndPassword(email, password).await()
            val firebaseUser = result.user

            firebaseUser?.let {
                Resource.Success(it.toUser())
            } ?: kotlin.run {
                Resource.Error("user not found")
            }
        } catch (e: Exception) {
            Resource.Error("Erorr:${e.message}")
        }
    }

    override fun logOut() {
        auth.signOut()
    }

}

private fun FirebaseUser.toUser():Users{
    return Users(
        userId = uid,
        userMail = email.toString(),
        userName = "",
        userLastName = "",
        userProfilUrl = photoUrl.toString()
    )
}
