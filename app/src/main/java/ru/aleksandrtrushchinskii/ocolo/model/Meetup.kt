package ru.aleksandrtrushchinskii.ocolo.model

import com.google.firebase.firestore.Exclude
import ru.aleksandrtrushchinskii.ocolo.common.EMPTY_DATE
import java.util.*


data class Meetup(@get:Exclude var id: String = "",
                  val userId: String = "",
                  val title: String = "",
                  val date: Date = EMPTY_DATE)