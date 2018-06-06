package ru.aleksandrtrushchinskii.ocolo.model

import com.google.firebase.firestore.Exclude
import ru.aleksandrtrushchinskii.ocolo.common.EMPTY_DATE
import java.util.*


data class Meetup(@get:Exclude var id: String = "",
                  var userId: String = "",
                  var title: String = "",
                  var date: Date = EMPTY_DATE)