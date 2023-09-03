package com.example.networktest

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.networktest.databinding.FragmentFirstBinding

//TODO: INSTANZIAMO IL ViewModel DENTRO AL FRAGMENT SCRIVENDO
// *NOMEViewModel*: *ViewModelCREATO* by activityViewModels()
// SE USIAMO IL ViewModel SOLAMENTE IN UNA SCHERMATA METTIAMO viewModels()
// SE INVECE LO USIAMO IN PIU' SCHERMATE ANDREMO A METTERE activityViewModels()
// PERCHE' ANDRA' AD IMPATTARE QUANTA MEMORIA UTILIZZA L'APP

class FirstFragment : Fragment() {

    private val catViewModel: CatViewModel by activityViewModels()

    private var _binding: FragmentFirstBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonFirst.setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }


        binding.submitButton.setOnClickListener {
            catViewModel.networkCall()
        }

        //TODO: CHIAMIAMO IL MutableLiveData PRESENTE NEL ViewModel E SETTIAMO UN OBSERVE.
        // IL CONTENUTO DELLA FUNZIONE VERRA' ESEGUITO OGNI QUALVOLTA IL CONTENUTO DELLA LIVE DATA CAMBIA
        // VA SPECIFICATO viewLifecycleOwner PERCHE' COSI' L'OBSERVE CAPISCE QUANDO IL FRAGMENT NON E'
        // PIU' VISUALIZZATO A SCHERMO E SMETTE DI OSSERVARE.

        catViewModel.result.observe(viewLifecycleOwner){
            setCatText(it)
        }

        catViewModel.isLoading.observe(viewLifecycleOwner){
            if(it){
                binding.networkLoading.visibility = View.VISIBLE
            } else {
                binding.networkLoading.visibility = View.GONE
            }
        }

    }

    //TODO: E' ALTAMENTE CONSIGLIATO CREARE DELLE FUNZIONI APPOSITE PER PULIZIA DEL CODICE

    private fun setCatText(it: CatData){
        binding.textviewFact.text = it.fact
        binding.textviewFactLength.text = "Fact ${it.length.toString()}"
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}