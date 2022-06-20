package es.upm.dit.isst.tfg.tfgwebapp.controller;

import java.security.Principal;
import java.util.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import es.upm.dit.isst.tfg.tfgwebapp.model.TFG;


@Controller

public class TFGController {

        public final String TFGMANAGER_STRING= "http://localhost:8083/tfgs/";

        public static final String VISTA_LISTA = "lista";

        public static final String VISTA_FORMULARIO = "formulario";

        private RestTemplate restTemplate = new RestTemplate();

        @GetMapping("/")

        public String inicio() {

                return "redirect:/" + VISTA_LISTA;

        }


        @GetMapping("/login")

        public String login() {

                return "redirect:/" + VISTA_LISTA;

        }


       @GetMapping("/lista")

        public String lista(Model model, Principal principal) {

                List<TFG> lista = new ArrayList<TFG>();

                if (principal == null || principal.getName().equals(""))

                        lista = Arrays.asList(restTemplate.getForEntity(TFGMANAGER_STRING,

                                           TFG[].class).getBody());

                else if (principal.getName().contains("@upm.es"))

                        lista = Arrays.asList(restTemplate.getForEntity(TFGMANAGER_STRING

                                    + "profesor/" + principal.getName()

                                              ,TFG[].class).getBody());

                else if (principal.getName().contains("@alumnos.upm.es")){

                        try { TFG tfg = restTemplate.getForObject(TFGMANAGER_STRING

                                    + principal.getName(), TFG.class);

                          if (tfg != null)

                                lista.add(tfg);

                        } catch (Exception e) {}

                }

                model.addAttribute("tfgs", lista);

                return VISTA_LISTA;

        }

        @GetMapping("/crear")

        public String crear(Map<String, Object> model) {

                TFG TFG = new TFG();

                model.put("TFG", TFG);

                model.put("accion", "guardar");

                return VISTA_FORMULARIO;

        }

        @PostMapping("/guardar")

        public String guardar(@Validated TFG TFG, BindingResult result) {

                if (result.hasErrors()) {

                        return VISTA_FORMULARIO;

                }

                try { restTemplate.postForObject(TFGMANAGER_STRING, TFG, TFG.class);

                } catch(Exception e) {}

                return "redirect:" + VISTA_LISTA;

        }

        @GetMapping("/editar/{id}")

        public String editar(@PathVariable(value = "id") String id,

                   Map<String, Object> model, Principal principal) {

                if (principal == null || ! principal.getName().equals(id))

                        return "redirect:/" + VISTA_LISTA;

                TFG tfg = null;

                try { tfg = restTemplate.getForObject(TFGMANAGER_STRING + id, TFG.class);

                } catch (HttpClientErrorException.NotFound ex) {}

                model.put("TFG", tfg);

                model.put("accion", "../actualizar"); //!

                return tfg != null ? VISTA_FORMULARIO : "redirect:/" + VISTA_LISTA;

        }

        @PostMapping("/actualizar")

        public String actualizar(@Validated TFG tfg, BindingResult result) {

                if (result.hasErrors()) {

                        return VISTA_FORMULARIO;

                }

                try { restTemplate.put(TFGMANAGER_STRING + tfg.getEmail(),

                           tfg, TFG.class);

                } catch(Exception e) {}

                return "redirect:" + VISTA_LISTA;

        }

        @GetMapping("/eliminar/{id}")

        public String eliminar(@PathVariable(value = "id") String id) {

               try{ restTemplate.delete(TFGMANAGER_STRING + id); }
               catch(Exception e) {}

                return "redirect:/" + VISTA_LISTA;

        }


       @GetMapping("/aceptar/{id}")

       public String aceptar(@PathVariable(value = "id") String id,

                       Map<String, Object> model, Principal principal) {

                if (principal != null) {

                        try { TFG tfg = restTemplate.getForObject(TFGMANAGER_STRING + id,

                                  TFG.class);

                                if (tfg != null

                                  && principal.getName().equals(tfg.getTutor())) {

                               restTemplate.postForObject(TFGMANAGER_STRING

                                  +tfg.getEmail()+"/incrementa", tfg, TFG.class);

                                  model.put("TFG", tfg);

                                }

                        } catch (HttpClientErrorException.NotFound ex) { }

                }

                return "redirect:/" + VISTA_LISTA;

        }






    
}
