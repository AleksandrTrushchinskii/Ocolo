package ru.aleksandrtrushchinskii.ocolo.ui.support.adapter

import android.view.View
import android.widget.Toast
import ru.aleksandrtrushchinskii.ocolo.BR
import ru.aleksandrtrushchinskii.ocolo.R
import ru.aleksandrtrushchinskii.ocolo.model.Meetup

object MeetupAdapter: ViewModelAdapter() {

    init {
        cell(Meetup::class.java, R.layout.meetup_item, BR.meetupItem)

        sharedObject(this, BR.meetupAdapter)
    }

    fun setData(data: List<Any>){
        items.clear()

        for (item in data){
            items.add(item)
        }

        notifyDataSetChanged()
    }

    fun itemClicked(view: View, meetup: Meetup){
        Toast.makeText(view.context, "Meetup is $meetup", Toast.LENGTH_SHORT).show()
    }
}