package com.ucaldas.mascotasfelices.controlador;

import java.util.Date;
import java.util.List;

import com.ucaldas.mascotasfelices.modelos.Atenciones;
import com.ucaldas.mascotasfelices.servicio.atencionesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.parameters.P;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ucaldas.mascotasfelices.modelos.Mascotas;
import com.ucaldas.mascotasfelices.modelos.Veterinarios;
import com.ucaldas.mascotasfelices.modelos.duenos;
import com.ucaldas.mascotasfelices.servicio.duenosService;
import com.ucaldas.mascotasfelices.servicio.mascotasService;
import com.ucaldas.mascotasfelices.servicio.veterinarioService;

@Controller
public class controlador {

    @Autowired
    duenosService duenosService;

    @Autowired
    veterinarioService veterinarioService;

    @Autowired
    mascotasService mascotasService;

    @Autowired
    atencionesService atencionesService;

    @GetMapping ("/")
    public String homeAdmin(){
        return "home"; //Llamamos al HTML
    }



      //DUENOS
      @GetMapping ("/VerDuenos")
      public String verDuenos(Model model, @ModelAttribute("mensaje") String mensaje){
          List<duenos> listaduenos=duenosService.getAllDuenos();
          model.addAttribute("listaduenos",listaduenos);
          model.addAttribute("mensaje",mensaje);
          return "verduenos"; //Llamamos al HTML
      }
  
      @GetMapping("/AgregarDueno")
      public String nuevoDueno(Model model, @ModelAttribute("mensaje") String mensaje){
          duenos dueno= new duenos();
          model.addAttribute("dueno",dueno);
          model.addAttribute("mensaje",mensaje);
          return "agregardueno";
      }
  
      @PostMapping("/GuardarDueno")
      public String guardarDueno(duenos dueno, RedirectAttributes redirectAttributes){
          if(duenosService.saveOrUpdateDueno(dueno)==true){
              redirectAttributes.addFlashAttribute("mensaje","saveOK");
              return "redirect:/VerDuenos";
          }
          redirectAttributes.addFlashAttribute("mensaje","saveError");
          return "redirect:/VerDuenos";
      }
  
      @GetMapping("/EditarDueno/{id}")
      public String editarDueno(Model model, @PathVariable Integer id, @ModelAttribute("mensaje") String mensaje){
          duenos dueno=duenosService.getDuenoById(id);
          model.addAttribute("dueno",dueno);
          model.addAttribute("mensaje", mensaje);
          return "editardueno";
      }
  
  
      @PostMapping("/ActualizarDueno")
      public String updateDueno(@ModelAttribute("dueno") duenos dueno, RedirectAttributes redirectAttributes){
          if(duenosService.saveOrUpdateDueno(dueno)){
              redirectAttributes.addFlashAttribute("mensaje","updateOK");
              return "redirect:/VerDuenos";
          }
          redirectAttributes.addFlashAttribute("mensaje","updateError");
          return "redirect:/EditarDueno/"+dueno.getId();
  
      }
  
      @GetMapping("/EliminarDueno/{id}")
      public String eliminarDueno(@PathVariable Integer id, RedirectAttributes redirectAttributes){
          if (duenosService.deleteDueno(id)==true){
              redirectAttributes.addFlashAttribute("mensaje","deleteOK");
              return "redirect:/VerDuenos";
          }
          redirectAttributes.addFlashAttribute("mensaje", "deleteError");
          return "redirect:/VerDuenos";
      }
  


      //´Veterinarios
    @GetMapping ("/VerVeterinarios")
    public String verVeterinarios(Model model, @ModelAttribute("mensaje") String mensaje){
        List<Veterinarios> listaVeterinarios=veterinarioService.getAllVeterinarios();
        model.addAttribute("listaVeterinarios",listaVeterinarios);
        model.addAttribute("mensaje",mensaje);
        return "verveterinarios"; //Llamamos al HTML
    }

    @GetMapping("/AgregarVeterinario")
    public String nuevoVeterinario(Model model, @ModelAttribute("mensaje") String mensaje){
        Veterinarios vet= new Veterinarios();
        model.addAttribute("vet",vet);
        model.addAttribute("mensaje",mensaje);
        return "agregarveterinario"; //Llamar HTML
    }

    @PostMapping("/GuardarVeterinario")
    public String guardarVeterinario(Veterinarios vet, RedirectAttributes redirectAttributes){
        String passEncriptada=passwordEncoder().encode(vet.getPassword());
        vet.setPassword(passEncriptada);
        if(veterinarioService.saveOrUpdateVeterinario(vet)==true){
            redirectAttributes.addFlashAttribute("mensaje","saveOK");
            return "redirect:/VerVeterinarios";
        }
        redirectAttributes.addFlashAttribute("mensaje","saveError");
        return "redirect:/AgregarVeterinario";
    }

    @GetMapping("/EditarVeterinario/{id}")
    public String editarVeterinario(Model model, @PathVariable Integer id, @ModelAttribute("mensaje") String mensaje){
        Veterinarios vet=veterinarioService.getVeterinarioById(id);
        model.addAttribute("vet",vet);
        model.addAttribute("mensaje", mensaje);
        return "editarveterinario";
    }

    @PostMapping("/ActualizarVeterinario")
    public String updateVeterinario(@ModelAttribute("vet") Veterinarios vet, RedirectAttributes redirectAttributes){
        Integer id=vet.getId(); //Sacamos el id del objeto empl
        String Oldpass=veterinarioService.getVeterinarioById(id).getPassword(); //Con ese id consultamos la contraseña que ya esta en la base
        if(!vet.getPassword().equals(Oldpass)){
            String passEncriptada=passwordEncoder().encode(vet.getPassword());
            vet.setPassword(passEncriptada);
        }
        if(veterinarioService.saveOrUpdateVeterinario(vet)){
            redirectAttributes.addFlashAttribute("mensaje","updateOK");
            return "redirect:/VerVeterinarios";
        }
        redirectAttributes.addFlashAttribute("mensaje","updateError");
        return "redirect:/EditarVeterinario/"+vet.getId();

    }

    @GetMapping("/EliminarVeterinario/{id}")
    public String eliminarVeterinario(@PathVariable Integer id, RedirectAttributes redirectAttributes){
        if (veterinarioService.deleteVeterinario(id)){
            redirectAttributes.addFlashAttribute("mensaje","deleteOK");
            return "redirect:/VerVeterinarios";
        }
        redirectAttributes.addFlashAttribute("mensaje", "deleteError");
        return "redirect:/VerVeterinarios";
    }


    //MASCOTAS
    @GetMapping ("/VerMascotas")
    public String viewMascotas(Model model, @ModelAttribute("mensaje") String mensaje){
        List<Mascotas> listaMascotas=mascotasService.getAllMascotas();
        model.addAttribute("listaMascotas",listaMascotas);
        model.addAttribute("mensaje",mensaje);
        return "vermascotas"; //Llamamos al HTML
    }

    @GetMapping("/AgregarMascota")
    public String nuevaMascota(Model model, @ModelAttribute("mensaje") String mensaje){
        Mascotas mascota= new Mascotas();
        model.addAttribute("mascota",mascota);
        model.addAttribute("mensaje",mensaje);
        List<duenos> listaDuenos= duenosService.getAllDuenos();
        model.addAttribute("listaDuenos",listaDuenos);
        Authentication auth= SecurityContextHolder.getContext().getAuthentication();
        String id=auth.getName();
        String nombreVet=veterinarioService.getVeterinarioById(Integer.valueOf(id)).getNombre();
        String apellido=veterinarioService.getVeterinarioById(Integer.valueOf(id)).getApellido();
        nombreVet= nombreVet+" "+apellido;
        model.addAttribute("nombreVet", nombreVet);
        return "agregarmascota"; //Llamar HTML
    }

    @PostMapping("/GuardarMascota")
    public String guardarMascota(Mascotas mascota, RedirectAttributes redirectAttributes){
        if(mascotasService.saveOrUpdateMascota(mascota)==true){
            redirectAttributes.addFlashAttribute("mensaje","saveOK");
            return "redirect:/VerMascotas";
        }
        redirectAttributes.addFlashAttribute("mensaje","saveError");
        return "redirect:/VerMascotas";
    }

    @GetMapping("/EditarMascota/{id}")
    public String editarMascota(Model model, @PathVariable Integer id, @ModelAttribute("mensaje") String mensaje){
        Mascotas mascota=mascotasService.getMascotaById(id);
        model.addAttribute("mascota",mascota);
        model.addAttribute("mensaje", mensaje);
        List<duenos> listaDuenos= duenosService.getAllDuenos();
        model.addAttribute("listaDuenos",listaDuenos);
        List<Veterinarios> listaVeterinarios=veterinarioService.getAllVeterinarios();
        model.addAttribute("listaVet", listaVeterinarios);
        return "editarmascota";
    }

    @PostMapping("/ActualizarMascota")
    public String updateMascota(@ModelAttribute("mascota") Mascotas mascota, RedirectAttributes redirectAttributes){
        if(mascotasService.saveOrUpdateMascota(mascota)){
            redirectAttributes.addFlashAttribute("mensaje","updateOK");
            return "redirect:/VerMascotas";
        }
        redirectAttributes.addFlashAttribute("mensaje","updateError");
        return "redirect:/EditarMascota/"+mascota.getId();

    }

    @GetMapping("/EliminarMascota/{id}")
    public String eliminarMascota(@PathVariable Integer id, RedirectAttributes redirectAttributes){
        if (mascotasService.deleteMascota(id)){
            redirectAttributes.addFlashAttribute("mensaje","deleteOK");
            return "redirect:/VerMascotas";
        }
        redirectAttributes.addFlashAttribute("mensaje", "deleteError");
        return "redirect:/VerMascotas";
    }

    @GetMapping("/Dueno/{id}/Mascotas") //Filtrar los mascotas por dueño
    public String verMascotasPorDueno(@PathVariable("id") Integer id, Model model){
        List<Mascotas> listaMascotas = mascotasService.obtenerPorDueno(id);
        model.addAttribute("listaMascotas",listaMascotas);
        return "VerMascotas"; //Llamamos al html con el emplelist de los empleados filtrados
    }

    @GetMapping("/Veterinario/{id}/Mascotas") //Filtrar los mascotas por Veterinario
    public String verMascotasPorVeterinario(@PathVariable("id") Integer id, Model model){
        List<Mascotas> listaMascotas = mascotasService.obtenerPorVeterinario(id);
        model.addAttribute("listaMascotas",listaMascotas);
        return "VerMascotas"; //Llamamos al html con el emplelist de los empleados filtrados
    }


    //ATENCIONES
    @GetMapping ("/VerAtenciones")
    public String viewAtenciones(Model model, @ModelAttribute("mensaje") String mensaje){
        List<Atenciones> listaAtenciones= atencionesService.getAllAtenciones();
        model.addAttribute("listaAtenciones",listaAtenciones);
        model.addAttribute("mensaje",mensaje);
        return "veratenciones"; //Llamamos al HTML
    }

    @GetMapping("/AgregarAtencion")
    public String nuevaAtencion(Model model, @ModelAttribute("mensaje") String mensaje){
        Atenciones atencion= new Atenciones();
        model.addAttribute("atencion",atencion);
        model.addAttribute("mensaje",mensaje);
        List<Mascotas> listaMascotas= mascotasService.getAllMascotas();
        model.addAttribute("listaMascotas",listaMascotas);
        Authentication auth= SecurityContextHolder.getContext().getAuthentication();
        String id=auth.getName();
        String nombreVet=veterinarioService.getVeterinarioById(Integer.valueOf(id)).getNombre();
        String apellido=veterinarioService.getVeterinarioById(Integer.valueOf(id)).getApellido();
        nombreVet= nombreVet+" "+apellido;
        model.addAttribute("nombreVet", nombreVet);
        return "agregaratencion"; //Llamar HTML
    }

    @PostMapping("/GuardarAtencion")
    public String guardarAtencion(Atenciones atencion, RedirectAttributes redirectAttributes){
        if(atencionesService.saveOrUpdateAtencion(atencion)==true){
            redirectAttributes.addFlashAttribute("mensaje","saveOK");
            return "redirect:/VerAtenciones";
        }
        redirectAttributes.addFlashAttribute("mensaje","saveError");
        return "redirect:/VerAtenciones";
    }

    @GetMapping("/EditarAtencion/{id}")
    public String editarAtencion(Model model, @PathVariable Integer id, @ModelAttribute("mensaje") String mensaje){
        Atenciones atencion=atencionesService.getAtencionById(id);
        model.addAttribute("atencion",atencion);
        model.addAttribute("mensaje", mensaje);
        List<Mascotas> listaMascotas= mascotasService.getAllMascotas();
        model.addAttribute("listaMascotas",listaMascotas);
        List<Veterinarios> listaVeterinarios=veterinarioService.getAllVeterinarios();
        model.addAttribute("listaVet", listaVeterinarios);
        return "editaratencion";
    }

    @PostMapping("/ActualizarAtencion")
    public String updateAtencion(@ModelAttribute("atencion") Atenciones atencion, RedirectAttributes redirectAttributes){
        if(atencionesService.saveOrUpdateAtencion(atencion)){
            redirectAttributes.addFlashAttribute("mensaje","updateOK");
            return "redirect:/VerAtenciones";
        }
        redirectAttributes.addFlashAttribute("mensaje","updateError");
        return "redirect:/EditarAtencion/"+atencion.getId();

    }

    @GetMapping("/EliminarAtencion/{id}")
    public String eliminarAtencion(@PathVariable Integer id, RedirectAttributes redirectAttributes){
        if (atencionesService.deleteAtencion(id)){
            redirectAttributes.addFlashAttribute("mensaje","deleteOK");
            return "redirect:/VerAtenciones";
        }
        redirectAttributes.addFlashAttribute("mensaje", "deleteError");
        return "redirect:/VerAtenciones";
    }

    @GetMapping("/Mascotas/{id}/Atenciones") //Filtrar los atenciones por mascota
    public String verAtencionesPorMascota(@PathVariable("id") Integer id, Model model){
        List<Atenciones> listaAtenciones = atencionesService.obtenerPorMascota(id);
        model.addAttribute("listaAtenciones",listaAtenciones);
        model.addAttribute("idMascota",id);
        Date fechainicial=atencionesService.obtenerPrimeraFecha(id);
        model.addAttribute("primeraFecha",fechainicial);
        return "veratencionesMascota"; //Llamamos al html con el emplelist de los empleados filtrados
    }

    @GetMapping("/Veterinario/{id}/Atenciones") //Filtrar los atenciones por Veterinario
    public String verAtencionesPorVeterinario(@PathVariable("id") Integer id, Model model){
        List<Atenciones> listaAtenciones = atencionesService.obtenerPorVeterinario(id);
        model.addAttribute("listaAtenciones",listaAtenciones);
        return "veratencionesVet"; //Llamamos al html con el emplelist de los empleados filtrados
    }

    @GetMapping("/Mascota/{id}/AgregarAtencion") //Filtrar los atenciones por Veterinario
    public String AtencionPorMascota(@PathVariable("id") Integer id, Model model, @ModelAttribute("mensaje") String mensaje){
        Atenciones atencion= new Atenciones();
        model.addAttribute("atencion",atencion);
        model.addAttribute("mensaje",mensaje);
        String nombreMascota=mascotasService.getMascotaById(id).getNombre();
        Integer idMascota=id;
        Authentication auth= SecurityContextHolder.getContext().getAuthentication();
        String idVet=auth.getName();
        String nombreVet=veterinarioService.getVeterinarioById(Integer.valueOf(idVet)).getNombre();
        String apellido=veterinarioService.getVeterinarioById(Integer.valueOf(idVet)).getApellido();
        nombreVet= nombreVet+" "+apellido;
        model.addAttribute("idMascota", idMascota);
        model.addAttribute("nombreMascota", nombreMascota);
        model.addAttribute("nombreVet", nombreVet);
        return "agregaratencionID"; //Llamar HTML
    }

    @PostMapping("/GuardarAtencion/{id}")
    public String guardarAtencionID(@PathVariable("id")Integer id, Atenciones atencion, RedirectAttributes redirectAttributes){
        if(atencionesService.saveOrUpdateAtencion(atencion)==true){
            redirectAttributes.addFlashAttribute("mensaje","saveOK");
            return "redirect:/Mascotas/"+id+"/Atenciones";
        }
        redirectAttributes.addFlashAttribute("mensaje","saveError");
        return "redirect:/Mascotas/"+id+"/Atenciones";
    }




  

    //Controlador que me lleva al template de No autorizado
    @RequestMapping(value="/Denegado")
    public String accesoDenegado(){
        return "accessDenied";
    }


    //Metodo para encriptar contraseñas
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    
}
