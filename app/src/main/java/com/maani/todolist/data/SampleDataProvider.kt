package com.maani.todolist.data

import java.util.*

class SampleDataProvider {

    companion object {
        private val simpletext1 = "A simple note"
        private val simpletext2 = "A note \n line feed"
        private val simpletext3 = """
            
            Lorem ipsum dolor sit amet, consectetur adipiscing elit. Etiam ac auctor nulla.
             Etiam lobortis urna mauris, non mattis massa lacinia consectetur. 
             Mauris convallis euismod nunc, sed egestas lorem ultrices eget. Quisque congue tincidunt ultricies. 
             Aenean iaculis, metus vitae scelerisque mollis, ante dolor ultricies turpis, vitae auctor lectus ipsum at urna.
             In porttitor, lectus non sollicitudin interdum, ipsum nulla tincidunt mauris, ac ultrices velit nisl sed dui. Vestibulum mattis mi leo, sit amet semper dui rutrum non. Aenean scelerisque est eget risus pulvinar posuere. Aenean porta nisl at quam dictum, nec maximus quam faucibus. Fusce at nisl viverra, luctus ex id, tincidunt eros. Donec eget nulla ut augue sollicitudin tempus sit amet sit amet lorem. Maecenas vel eros bibendum justo pulvinar gravida in quis lorem. Proin at vestibulum risus, eget porta odio. Quisque et lacus arcu. Aenean aliquet dapibus dictum. Nam a suscipit metus.

            Mauris sed interdum elit. Cras ut lectus in urna vehicula dictum. Proin rhoncus nulla nec laoreet tristique. Mauris tempus mollis elit, a varius sapien. Nunc blandit ornare sollicitudin. Nam sit amet lorem quis est luctus ornare. Ut eget purus in ligula feugiat scelerisque. Vestibulum est sapien, dapibus et accumsan sit amet, elementum vitae dolor. Fusce vulputate, enim in tristique volutpat, dolor tellus eleifend nibh, eget mattis nulla enim et quam.
            
        """.trimIndent()

        private fun getDate(diff: Long): Date {
            return Date(Date().time + diff)
        }

        fun getNotes() = arrayListOf(
            NoteEntity( getDate(0), simpletext1),
            NoteEntity( getDate(1), simpletext2),
            NoteEntity( getDate(2), simpletext3)
        )
    }
}