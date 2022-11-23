package com.pedrozoia.splitthebill.adaptor

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.pedrozoia.splitthebill.R
import com.pedrozoia.splitthebill.model.Person

class PersonAdapter (
    context: Context,
    private val personList: MutableList<Person>
    ) : ArrayAdapter<Person>(context, R.layout.tile_person, personList) {
        private data class TilePersonHolder(val nameTv: TextView, val spentTv: TextView, val debtTv: TextView)

        override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
            val person = personList[position]
            var personTileView = convertView
            if (personTileView == null) {
                personTileView =
                    (context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater).inflate(
                        R.layout.tile_person,
                        parent,
                        false
                    )

                val tilePersonHolder = TilePersonHolder(
                    personTileView.findViewById(R.id.nomeTv),
                    personTileView.findViewById(R.id.valorGastoTv),
                    personTileView.findViewById(R.id.debitoTv)
                )
                personTileView.tag = tilePersonHolder
            }

            with(personTileView?.tag as TilePersonHolder) {
                nameTv.text = person.nome
                spentTv.text = "Gastou: " + person.valorGasto
                if(person.debito.toDouble() < 0){
                    person.debito = (person.debito.toDouble() * -1).toString()
                    debtTv.text = "Total a receber: R$" + person.debito
                }
                else debtTv.text = "Total a pagar: R$" + person.debito
            }

            return personTileView
        }
}