package com.pedrozoia.splitthebill.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.pedrozoia.splitthebill.databinding.ActivityPersonBinding
import com.pedrozoia.splitthebill.model.Constant.EXTRA_PERSON
import com.pedrozoia.splitthebill.model.Constant.VIEW_PERSON
import com.pedrozoia.splitthebill.model.Person
import kotlin.random.Random

class PersonActivity : AppCompatActivity() {
    private val apb: ActivityPersonBinding by lazy {
        ActivityPersonBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(apb.root)

        val receivedPerson = intent.getParcelableExtra<Person>(EXTRA_PERSON)
        receivedPerson?.let{ _receivePerson ->
            with(apb) {
                with(_receivePerson) {
                    nomeEt.setText(nome)
                    valorGastoEt.setText(valorGasto)
                    debitoEt.setText(debito)
                    descricaoEt.setText(descricao)
                }
            }
        }
        val viewPerson = intent.getBooleanExtra(VIEW_PERSON, false)
        if (viewPerson) {
            apb.nomeEt.isEnabled = false
            apb.valorGastoEt.isEnabled = false
            apb.debitoEt.isEnabled = false
            apb.descricaoEt.isEnabled = false
            apb.saveBt.visibility = View.GONE
        }

        apb.saveBt.setOnClickListener {
            val person = Person(
                id = receivedPerson?.id?: Random(System.currentTimeMillis()).nextInt(),
                nome = apb.nomeEt.text.toString(),
                valorGasto = apb.valorGastoEt.text.toString(),
                debito = apb.debitoEt.text.toString(),
                descricao = apb.descricaoEt.text.toString(),
            )
            val resultIntent = Intent()
            resultIntent.putExtra(EXTRA_PERSON, person)
            setResult(RESULT_OK, resultIntent)
            finish()
        }
    }
}