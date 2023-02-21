package com.secuite.secur.controlleur;


import com.secuite.secur.modeles.*;
import com.secuite.secur.repository.*;
import com.secuite.secur.services.servuser;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.io.IOException;
import java.time.*;
import java.util.*;

@RestController
@CrossOrigin("*")
public class usercontrolleur {


    @Autowired
    private final servuser service;
    @Autowired
    private final reporole rol;

    @Autowired
    private final reponote notte;

    @Autowired
    private final repoprojet proj;

    @Autowired
    private final repoadmin ads;

    @Autowired
    private final reposoutenance sout;


    @Autowired
    private final repojury jur;


    @Autowired
    private final repoetudiant etudiant;
    @Autowired
    private final reposoutenance soutenan;


    @Autowired
    private final repopromo promo;

    @Autowired
    private final repoformation  format;

    @Autowired
    private final repouser user;

    public usercontrolleur( servuser service, reporole rol, reponote notte, repoprojet proj, repoadmin ads, reposoutenance sout, repojury jur, repoetudiant etudiant, reposoutenance soutenan, repopromo promo, repoformation format, repouser user, repojury jurys, repocriteres critere) {

        this.service = service;
        this.rol = rol;
        this.notte = notte;
        this.proj = proj;
        this.ads = ads;
        this.sout = sout;
        this.jur = jur;
        this.etudiant = etudiant;
        this.soutenan = soutenan;
        this.promo = promo;
        this.format = format;
        this.user = user;
        this.jurys = jurys;
        this.critere = critere;
    }

    @Autowired
    private final repojury jurys;

    @Autowired
    private final repocriteres critere;


    @PostMapping("/ajoutetudiant/{nom}/{prenom}/{username}/{mot2passe}/{prom}/{nomsout}")
    // @PreAuthorize("hasRole('ADMIN')")
    public Object create(@RequestParam("file") MultipartFile file, @PathVariable String nom, @PathVariable String prenom, @PathVariable String username, @PathVariable String mot2passe, @PathVariable("prom") int prom,@PathVariable String nomsout) {
        String a = file.getOriginalFilename();
        role r = rol.findByRolename("etudiant");
        System.out.println("la promotionnnnnn");
        System.out.println("alors la promotion marccheeee");
        String Path = "C:\\Users\\Fatoumata DEMBELE\\Desktop\\securityvraisoutenance\\securite\\secur\\src\\main\\resources\\img";
        System.out.println("le path n'as pas de probleme");
        try {
            System.out.println("on est rentrer dans try");
            //  Files.copy(file.getInputStream(), Paths.get(Path + File.separator + file.getOriginalFilename()));
            System.out.println("la c'est essay etudiant");
            Etudiant o = new Etudiant();
            //Etudiant o = new Etudiant( nom, prenom, username,  mot2passe, r, (Collection<promotion>) p);
            System.out.println("on va tester la promotion");
            System.out.println(o.getPromo());
            o.setNom(nom);
            o.setLu(false);
            o.setPrenom(prenom);
            o.setUsername(service.generateUsername(prenom,nom));
            o.setMot2passe(mot2passe);
            o.setSoutenances(soutenan.findByNom(nomsout));
            System.out.println("on va tester la promotion encore");
            // o.getPromo().add(p);
            System.out.println("on va tester la promotion");
            // o.setImg(a);
            o.setRoles(r);
            System.out.println(o.getPromo());
            System.out.println(o.getNom());
            if (nom.equals(null) || prenom.equals(null) || username.equals(null) || mot2passe.equals(null) ) {
                return "veillez remplir tout les champs ";
            } else{
                System.out.println("le moment de blocker ");
                System.out.println(o.getNom());

                utilisateur m= user.findByNom(o.getNom());
                //   p= new promotion(2,"dfgh",new Date(),new Date(),new formation());
                promotion   p=promo.findById(prom);
//                if (o.getPromo() != null) {
//                    o.getPromo().add(p);
//                }
                if (o.getPromo() == null) {
                    //  o.setPromo(new ArrayList<Promotion>(p));
                    o.setPromo(new ArrayList<promotion>());
                    // o.setPromo((Collection<promotion>) p);
                    o.getPromo().add(p);
                    service.enregistreretudiant(o);
                }
                else {
                    System.out.println("erreurrrrrrrrr");
                    // gérer le cas où la propriété promo n'est pas définie
                }



                // o.setPromo((Collection<promotion>)p);
                //  o.getPromo().add(p);


                // service.ajoutele(nom,prom);
                //o.setUsername(service.generateUsername(nom,prenom));
                //  o.getPromo().add(promo.findById(1));

                System.out.println("la relationnnnnn");

                // o.getPromo().add(p);
//                promotion pi=promo.findByNom(prom);
//                user.find(o.getIde(),pi.getId()
                ;
                System.out.println(o.getPromo());
                return "bien enregistrer";
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }

    @PostMapping("/ajoutjury/{nom}/{prenom}/{username}/{mot2passe}/{prom}/{voir}")
    // @PreAuthorize("hasRole('ADMIN')")
    public Object createjury(@RequestParam("file") MultipartFile file, @PathVariable("nom") String nom, @PathVariable("prenom") String prenom, @PathVariable("username") String username, @PathVariable("mot2passe") String mot2passe, @PathVariable("prom") String prom,@PathVariable String voir) {
//        System.out.println(file.getOriginalFilename());
//        System.out.println(file.getName());
//        System.out.println(file.getContentType());
//        System.out.println(file.getSize());
//        String a = file.getOriginalFilename();
//        role r = rol.findByRolename("jury");
//        promotion p = promo.findByNom(prom);
//
//        String Path = "C:\\Users\\Fatoumata DEMBELE\\Downloads\\projetsoutenance\\src\\main\\resources\\img";
//        try {
//            Files.copy(file.getInputStream(), Paths.get(Path + File.separator + file.getOriginalFilename()));
////            jury o = new jury((int) 0, nom, prenom, username, a, mot2passe, r, (Collection<promotion>) promo, null);
////            if (nom.equals(null) || prenom.equals(null) || username.equals(null) || mot2passe.equals(null) || p == null) {
////                return "veillez remplir tout les champs ";
////            } else{
////                o.setUsername(service.generateUsername(nom,prenom));
////                return service.ajoutjury(o);
////            }
//
//
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }


        System.out.println(file.getOriginalFilename());
        System.out.println(file.getName());
        System.out.println(file.getContentType());
        System.out.println(file.getSize());
        String a = file.getOriginalFilename();
        role r = rol.findByRolename(voir);
        System.out.println("la promotionnnnnn");
        //  promotion p = promo.findById(prom);
//        System.out.println(p.getNom());
//        System.out.println(p.getDatefin());
        System.out.println("alors la promotion marccheeee");

        String Path = "C:\\Users\\Fatoumata DEMBELE\\Desktop\\securityvraisoutenance\\securite\\secur\\src\\main\\resources\\img";
        System.out.println("le path n'as pas de probleme");
        try {
            System.out.println("on est rentrer dans try");
            //   Files.copy(file.getInputStream(), Paths.get(Path + File.separator + file.getOriginalFilename()));
            System.out.println("la c'est essay etudiant");
            if (voir.equals("jury")){
                jury o = new jury();
                //Etudiant o = new Etudiant( nom, prenom, username,  mot2passe, r, (Collection<promotion>) p);
                System.out.println("on va tester la promotion");
                System.out.println(o.getPromo());
                o.setNom(nom);
                o.setPrenom(prenom);
                o.setUsername(service.generateUsername(prenom,nom));
                o.setMot2passe(mot2passe);
                System.out.println("on va tester la promotion encore");
                // o.getPromo().add(p);
                System.out.println("on va tester la promotion");
                //  o.setImg(a);
                o.setRoles(r);
                System.out.println(o.getPromo());
                System.out.println(o.getNom());
                if (nom.equals(null) || prenom.equals(null) || username.equals(null) || mot2passe.equals(null) ) {
                    return "veillez remplir tout les champs ";
                } else{
                    System.out.println("le moment de blocker ");
                    System.out.println(o.getNom());

                    utilisateur m= user.findByNom(o.getNom());
                    //   p= new promotion(2,"dfgh",new Date(),new Date(),new formation());
                    promotion   p=promo.findByNom(prom);
//                if (o.getPromo() != null) {
//                    o.getPromo().add(p);
//                }
                    if (o.getPromo() == null) {
                        //  o.setPromo(new ArrayList<Promotion>(p));
                        o.setPromo(new ArrayList<promotion>());
                        // o.setPromo((Collection<promotion>) p);
                        o.getPromo().add(p);
                        service.ajoutjury(o);
                    }
                    else {
                        System.out.println("erreurrrrrrrrr");
                        // gérer le cas où la propriété promo n'est pas définie
                    }



                    // o.setPromo((Collection<promotion>)p);
                    //  o.getPromo().add(p);


                    // service.ajoutele(nom,prom);
                    //o.setUsername(service.generateUsername(nom,prenom));
                    //  o.getPromo().add(promo.findById(1));

                    System.out.println("la relationnnnnn");

                    // o.getPromo().add(p);
//                promotion pi=promo.findByNom(prom);
//                user.find(o.getIde(),pi.getId()
                    ;
                    System.out.println(o.getPromo());
                    return "bien enregistrer";
                }

            }
            else if (voir.equals("admin")){
                admin o = new admin();
                //Etudiant o = new Etudiant( nom, prenom, username,  mot2passe, r, (Collection<promotion>) p);
                System.out.println("on va tester la promotion");
                System.out.println(o.getPromo());
                o.setNom(nom);
                o.setPrenom(prenom);
                o.setUsername(service.generateUsername(prenom,nom));
                o.setMot2passe(mot2passe);
                System.out.println("on va tester la promotion encore");
                // o.getPromo().add(p);
                System.out.println("on va tester la promotion");
                o.setImg(a);
                o.setRoles(r);
                System.out.println(o.getPromo());
                System.out.println(o.getNom());
                if (nom.equals(null) || prenom.equals(null) || username.equals(null) || mot2passe.equals(null) ) {
                    return "veillez remplir tout les champs ";
                } else{
                    System.out.println("le moment de blocker ");
                    System.out.println(o.getNom());

                    utilisateur m= user.findByNom(o.getNom());
                    //   p= new promotion(2,"dfgh",new Date(),new Date(),new formation());
                    promotion   p=promo.findByNom(prom);
//                if (o.getPromo() != null) {
//                    o.getPromo().add(p);
//                }
                    if (o.getPromo() == null) {
                        //  o.setPromo(new ArrayList<Promotion>(p));
                        o.setPromo(new ArrayList<promotion>());
                        // o.setPromo((Collection<promotion>) p);
                        o.getPromo().add(p);
                        service.enreisteradmin(o);
                    }
                    else {
                        System.out.println("erreurrrrrrrrr");
                        // gérer le cas où la propriété promo n'est pas définie
                    }



                    // o.setPromo((Collection<promotion>)p);
                    //  o.getPromo().add(p);


                    // service.ajoutele(nom,prom);
                    //o.setUsername(service.generateUsername(nom,prenom));
                    //  o.getPromo().add(promo.findById(1));

                    System.out.println("la relationnnnnn");

                    // o.getPromo().add(p);
//                promotion pi=promo.findByNom(prom);
//                user.find(o.getIde(),pi.getId()
                    ;
                    System.out.println(o.getPromo());
                    return "bien enregistrer";
                }
            }
            else {
                return "erreur de gestion de role";
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
//        catch (IOException e) {
//            throw new RuntimeException(e);
//        }


    }

//        @GetMapping("/readea")
    // @PreAuthorize("hasRole('USER')")
//    public List<utilisateur> readd( String type){
//        return service.lire(type);
//    }

    @GetMapping("/ajoutnote/{idprojet}/{idjury}/{nom}/{criterenom}/{note}/{commentaire}")
//     @PreAuthorize("hasRole('ADMIN')")
    public Object createjury(@PathVariable("idprojet") int idproget,@PathVariable("idjury") int idjury, @PathVariable("nom") String nom, @PathVariable("criterenom") int criterenom, @PathVariable("note") int note, @PathVariable("commentaire") String commentaire) {
        System.out.println("le etudiantttttttttttt");

        Etudiant m= etudiant.findByIde(idproget);
        System.out.println(m);


        jury r=    jur.findByIde(idjury);
        System.out.println("le juryyyyyyyyyyyyyyyyy");
        System.out.println(r);
        criteresevaluation c=  critere.findByIdo(criterenom);
        lesnotes mo=new lesnotes(null,note,true,commentaire,new Date(),m,r,c);
        return service.ajoutnotes(mo);
    }
    @GetMapping("/readea")
    public Object infouser(int o) {
        return service.donneeutilisateur(o);
    }

    @GetMapping("/readea/{o}/{m}")
    public Object affichernoteetudiant(@PathVariable int o, @PathVariable int m) {
        return service.affichernoteetudiant(o, m);
    }

    @GetMapping("/readea/{o}")
    public Object afficherdetailetudiant(@PathVariable int o) {
        return service.afficherdetailuser(o);
    }

//    @PostMapping("/ajoutjury/{nom}/{prenom}/{username}/{mot2passe}/{prom}")
    // @PreAuthorize("hasRole('ADMIN')")
//  public Object createjury(@PathVariable String nomprojet,@PathVariable String nomjury, @PathVariable String nomcritere, @RequestBody int note, @RequestBody String commentaire,  @RequestBody String prom) {
//       projet j=proj.findByNom(nomprojet);
//       jury u=jurys.findByNom(nomjury);
//       role i=u.getRoles();
//       criteresevaluation c=critere.findByNom(nomcritere);

//       if (i.getRolename()=="jury"){
//           lesnotes um=new lesnotes(null,note,commentaire,new Date(),j,u,c);
//           service.ajoutnotes(um);
//        return "enregistrement effectuee avec succes";
//}
//    else {
//    return "vous n'etes pas un jury";
//    }
//    }
//    @PostMapping("/readea/{o}")
//    public Double calculenoteetudiantparlastarfatoumata(@PathVariable Long o,@PathVariable Long g) {
////        return service.afficherdetailuser(o);
//        Double t=0.0; Double coef=0.0; Double m;
//        List<Object[]> notes = user.lesnotesdeetudiantparrapportaunjuwsxryspecifique(o, g);
//        for (Object[] note : notes) {
//            Long userId = (Long) note[0];
//            int noteId = (int) note[1];
//            String comment = (String) note[2];
//            Long criteriaId = (Long) note[3];
//            int coefficient = (int) note[4];
//            t=t+noteId;
//            coef= coef+coefficient;

    // do something with the data
//            System.out.println("userId :" + userId + " noteId :" + noteId + " comment :" + comment + " criteriaId :" + criteriaId + " coefficient :" + coefficient);
//        }
//
//        return t/coef;
//    }
    @PostMapping("/readea/{o}")
    public Double calculenotetotaletudiantparrapportajoliejury(@PathVariable("o") int o) {
//        return service.afficherdetailuser(o);
        Double t=0.0; Double coef=0.0; Double m; int
                a;
        System.out.println("bienvenue");
        List<Object[]> notes = user.lesnotesdeetudiantparrapportatoutjury(o);
        System.out.println("maiintenant on rentre dans la boucle");
        for (Object[] note : notes) {
            int userId = (int) note[0];
            int noteId = (int) note[1];
            System.out.println("la liste3");
            String comment = (String) note[2];
            int criteriaId = (int) note[3];
            int coefficient = (int) note[4];
            t=t+noteId;
            System.out.println("la valeur de t");
            System.out.println(t);
//            coef= coef+coefficient;
            // do something with the data
            System.out.println("userId :" + userId + " noteId :" + noteId + " comment :" + comment + " criteriaId :" + criteriaId + " coefficient :" + coefficient);
        }
        System.out.println("la valeur total de t");
        System.out.println(t);
        a=  user.lesjurysparrapportaletudiant(o);
        return t/a;
    }
    @GetMapping("/lalistedesresultat/{o}")
    public List<resultatvote> jesperfonctionner(@PathVariable("o") int o) {
        System.out.println("la listeeeeeeeeeeeeeeee");
        // System.out.println(o);
        List <Object[]> liste=user.lesetudiiantsparrapportaunepromotion(o);
        System.out.println(o);
        System.out.println("aussi");
        System.out.println(liste);
        List<resultatvote> l = new ArrayList<>();
        System.out.println("aussi2");
        int i=0;
        for (Object[] etud : liste) {
            System.out.println("la liste3");
            int userid = (int) etud[0];
            System.out.println("la liste4");
            System.out.println(userid);
            String nom = (String) etud[1];
            System.out.println("la liste5");
            System.out.println(nom);
            //String nomprojet = (String) etud[2];
            System.out.println("la liste6");
            //  System.out.println(nomprojet);
            Double t = 0.0;
            Double coef = 0.0;
            System.out.println("la liste2");
            Double m;
            List<Object[]> notes = user.lesnotesdeetudiantparrapportatoutjury(userid);
            for (Object[] note : notes) {
                int userId = (int) note[0];
                int noteId = (int) note[1];
                System.out.println("la liste3");
                String comment = (String) note[2];
                int criteriaId = (int) note[3];
                int coefficient = (int) note[4];
                t=t+noteId;
                System.out.println("la valeur de t");
                coef= coef+coefficient;
                // do something with the data
                System.out.println("userId :" + userId + " noteId :" + noteId + " comment :" + comment + " criteriaId :" + criteriaId + " coefficient :" + coefficient);
            }
            int   a=  user.lesjurysparrapportaletudiant(userid);
            Double r= t/a;
            System.out.println("voici le t");
            System.out.println(t);
            System.out.println("voici le t");
            System.out.println(t);
            System.out.println("voici le r");
            System.out.println(r);
            resultatvote result=new resultatvote();
            result.setNom(nom);
            result.setPrenom("nomduprojet");
            result.setAutrenom("nompromotionici");
            result.setResultat(r);
            System.out.println(result.getResultat());
            l.add(result);
        }
        return  l;
    }
    @GetMapping("/lalistealors/{p}")
    public Object listi( @PathVariable int p){
        System.out.println("la listeeeeeeeeeeeeeeee");
//        user.essay(o);
//        List<Object> liste=new ArrayList<>();
//        liste=user.lesetudiiantsparrapportaunepromotion(o);
//        notte.findByEtudiantAndJurysAndCriteres(etudiant.findByIde(o),jur.findByIde(m),critere.findByIdo(c));
        System.out.println("la listeeeeeeeeeeeeeeee");
        // System.out.println(user.essay(o));
        return  user.lesjurysparrapportaletudiant(p);            //  notte.findByEtudiantAndJurysAndCriteres(etudiant.findByIde(o),jur.findByIde(m),critere.findByIdo(c));
    }
    @PostMapping("/creationpromo/{nom}/{datedebut}/{datefin}/{forma}")
    public String creerpromo(@PathVariable String nom,@PathVariable String datedebut,@PathVariable String datefin,@PathVariable String forma) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        formation form=format.findByNom(forma);
        promotion p=new promotion(0,nom,new Date(sdf.parse(datedebut).getTime()),new Date(),form);
        if (promo.findByNom(nom)!=null){
            return "cette promotion existe deja";
        }
        else {
            service.enregistrer(p);
            return "enregistrement de la promotion fait avec succes";
        }
    }
    @PostMapping("/creationpromo/{nom}/{datedebut}/{forma}")
    public String enregistrercritereevaluations(@PathVariable String nom,@PathVariable int datedebut,@PathVariable String forma) throws ParseException {
        formation form=format.findByNom(forma);
        criteresevaluation e=new criteresevaluation(0,nom,datedebut,form);
        if(critere.findByNomAndFormations(nom,form)!=null){
            return  "ce criteres existe deja dans cette formation";
        }
        else {
            service.ajoutcriteres(e);
            return "critere enregistrer  dans cette formation avec succes";
        }
    }
    @PostMapping("/creationprojet/{nom}/{datedebut}/{forma}")
    public String enregistrerprojet( @PathVariable String nom, @PathVariable String datedebut, @PathVariable String forma)  {
        Etudiant e=etudiant.findByUsername(forma);
        if (nom.equals(null) || datedebut.equals(null) || forma.equals(null)) {
            return "veillez renplir tout les champs";
        }
        else
        {
            projet d=new projet(0,nom,datedebut,e);
            service.enreistererprojets(d);
            return "bien enregistrer";
        }
    }
    @PostMapping("/creationprojet/{nomsout}/{datee}/{heuredeb}/{heurefin}/{nomj}")
    public String ajoutsoutenance( @RequestParam("file") MultipartFile file,@PathVariable String nomsout,@PathVariable String datee, @PathVariable String heuredeb, @PathVariable String heurefin, @PathVariable String nomj) throws ParseException, IOException {
        jury h=jur.findByNom(nomj);
        System.out.println(file.getOriginalFilename());
        System.out.println(file.getName());
        System.out.println(file.getContentType());
        System.out.println(file.getSize());
        String a = file.getOriginalFilename();
        String Path = "C:\\Users\\Fatoumata DEMBELE\\Desktop\\securityvraisoutenance\\securite\\secur\\src\\main\\resources\\img";
        // Files.copy(file.getInputStream(), Paths.get(Path + File.separator + file.getOriginalFilename()));
        LocalTime defenseTime = LocalTime.parse("09:00:00");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        soutenance s= new soutenance();
        //  (0, new Date(sdf.parse(datee).getTime()), new Date(sdf.parse(datedebut).getTime()),LocalTime.parse(heuredeb),LocalTime.parse(heurefin),h);
        // s.setImg(a);
        s.setDatedebut(new Date(sdf.parse(datee).getTime()));
        s.setNom(nomsout);
        s.setHeuredeb(LocalTime.parse(heuredeb));
        s.setHeurefin(LocalTime.parse(heurefin));
        if (s.getJurys() == null) {
            //  o.setPromo(new ArrayList<Promotion>(p));
            s.setJurys(new ArrayList<jury>());
            // o.setPromo((Collection<promotion>) p);
            s.getJurys().add(h);
//         soutenance l=sout.findFirstByJurysAndDatedebutLessThanEqualAndDatefinGreaterThanEqualAndHeuredebLessThanEqualAndHeurefinGreaterThanEqual(h,new Date(),new Date(),LocalTime.now(),LocalTime.now());
            //  List<soutenance> g=sout.findByJurysAndDatedebutLessThanEqualAndHeuredebLessThanEqualAndHeurefinGreaterThanEqual(h,new Date(),LocalTime.now(),LocalTime.now());

            if (isJuryAvailable(h, new Date(sdf.parse(datee).getTime()), LocalTime.parse(heuredeb), LocalTime.parse(heurefin))) {
                service.enreisterersout(s);
                return "enregistrer";
            } else {
                return "cet jury a deja une soutenance dans cette plage de temps";
            }
        }


        else
            return " mauvaise enregistrement de la soutenance";
    }
    @PostMapping("/creationformation/{nom}/{desc}/{obj}")
    public String enregistrerformation( @PathVariable String nom, @PathVariable String desc, @PathVariable String obj) {
        if (nom.equals(null) || desc.equals(null) || obj.equals(null) ) {
            return "veillez renseiner tout les champs";
        }
        else {
            formation f=new formation(0L,nom,desc,obj);
            format.save(f);
            return "formation enregistrer avec succes";
        }}
    // la suppression
    @DeleteMapping("/delete/{idPostulant}")
    public String supprimerformation( @PathVariable String idPostulant) {
        formation f=format.findByNom(idPostulant);
        format.deleteById(f.getId()); // Supprimer l'ID;
        return "formation  Supprimer !";
    }
    @DeleteMapping("/sup/{idPostulant}")
    public String supprimerjury( @PathVariable int idPostulant) {
        //  formation f=format.findByNom(idPostulant);
        jur.deleteById(idPostulant); // Supprimer l'ID;
        return "jury  Supprimer !";
    }
    @DeleteMapping("/supprimeretudiant/{idPostulant}")
    public String supprimeretudiant( @PathVariable int idPostulant) {
        //  formation f=format.findByNom(idPostulant);
        etudiant.deleteById(idPostulant); // Supprimer l'ID;
        return "jury  Supprimer !";
    }
    @DeleteMapping("/supprimerpromotion/{idPostulant}")
    public String supprimepromotion( @PathVariable int idPostulant) {
        //  formation f=format.findByNom(idPostulant);
        promo.deleteById(idPostulant); // Supprimer l'ID;
        return "jury  Supprimer !";
    }
    @DeleteMapping("/supprimerprojet/{idPostulant}")
    public String supprimerprojet( @PathVariable int idPostulant) {
        //  formation f=format.findByNom(idPostulant);
        proj.deleteById(idPostulant); // Supprimer l'ID;
        return "jury  Supprimer !";
    }
    @DeleteMapping("/supprimeradmin/{idPostulant}")
    public String supprimeradmin( @PathVariable int idPostulant) {
        //  formation f=format.findByNom(idPostulant);
        ads.deleteById(idPostulant); // Supprimer l'ID;
        return "jury  Supprimer !";
    }
    @GetMapping("/lalistedesetudiants")
    public List<Etudiant> lire() { // la méthode (findAll) pour la lecture ou liste des postulants;
        return etudiant.findAll();
    }

    @GetMapping("/lalistedesadmin")
    public List<admin> lireadmin() { // la méthode (findAll) pour la lecture ou liste des postulants;
        return ads.findAll();
    }
    @GetMapping("/lalistedesjurys")
    public List<jury> lirejury() { // la méthode (findAll) pour la lecture ou liste des postulants;
        return jurys.findAll();
    }
    @GetMapping("/lalistedesformation")
    public List<formation> lireformation() { // la méthode (findAll) pour la lecture ou liste des postulants;
        return format.findAll();
    }

    @GetMapping("/lalistedesformation/{nom}")
    public List<promotion> lirepromotionparformation(@PathVariable String nom) { // la méthode (findAll) pour la lecture ou liste des postulants;
        formation f=format.findByNom(nom);
        return promo.findByFormations(f);
    }
    @GetMapping("/lalisteeee/{nom}")
    public List<Object> lireetudiantprojetpromotionparformation(@PathVariable int nom) { // la méthode (findAll) pour la lecture ou liste des postulants;
        //formation f=format.findByNom(nom);
        return user.etudiantlisteparpromotiondeformation(nom);
    }
    // a faire la liste des projets et leur etudiants dans une promotion d'une formation
//@GetMapping("/lalistedesformation")
//public List<criteresevaluation> lirecriteresparformation() { // la méthode (findAll) pour la lecture ou liste des postulants;
//    return critere.findAllByFormations();
//}
    @GetMapping("/ladate/{n}")
    public Object lireetudia(@PathVariable String n) { // la méthode (findAll) pour la lecture ou liste des postulants;
        formation f=format.findByNom(n);
        return user.ladatedeformationrecent(f.getId(),new Date());
    }

    @GetMapping("/infoetud/{n}")
    public Object infoetud(@PathVariable int n) { // la méthode (findAll) pour la lecture ou liste des postulants;
        //formation f=format.findByNom(n);
        return user.infoetudiant(n);
    }
    @GetMapping("/infocritere/{n}")
    public List<Object> infocritere(@PathVariable int n) { // la méthode (findAll) pour la lecture ou liste des postulants;
        //formation f=format.findByNom(n);
        return user.criteresparuser(n);}
    @PostMapping("/creationvote/{note}/{commentaires}/{etudian}/{jury}/{critereid}")
    public String enregistrervote( @PathVariable int note, @PathVariable("commentaires") String commentaire, @PathVariable int etudian,@PathVariable int jury,@PathVariable int critereid) {
        Etudiant l=etudiant.findByIde(etudian);
        Object k= user.soutenanceetudiant(l.getIde());
        Boolean s = Boolean.parseBoolean(k.toString());
        com.secuite.secur.modeles.jury j=jur.findByIde(jury);
        criteresevaluation c=critere.findByIdo(critereid);
        lesnotes m=  notte.findByEtudiantAndJurysAndCriteres(l,j,c);

        if (m == null) {
            lesnotes e=new lesnotes((Long) null, (Integer) note,false,commentaire,new Date(),l,j,c);
            notte.save(e);
            return "note enregistrer avec succes";
        } else {
            notte.findById(m.getId())
                    .map(p->{
                        p.setNote((Integer) note);
                        p.setCommentaires(commentaire);
                        return  notte.save(p);
                    });
            return "bien";
        }


    }
    @CrossOrigin(origins = "http://localhost:8100")
    @PostMapping("/creationa/{notee}/{commentaires}/{etudian}/{jury}/{critereid}")
    public void enregistrervotepar( @PathVariable boolean notee, @PathVariable("commentaires") String commentaire, @PathVariable int etudian,@PathVariable int jury,@PathVariable int critereid) {
        Etudiant l=etudiant.findByIde(etudian);
        Object k= user.soutenanceetudiant(l.getIde());
        boolean s = Boolean.parseBoolean(k.toString());
        com.secuite.secur.modeles.jury j=jur.findByIde(jury);
        criteresevaluation c=critere.findByIdo(critereid);
        lesnotes m=  notte.findByEtudiantAndJurysAndCriteres(l,j,c);
        if (m == null) {
            lesnotes e=new lesnotes((Long) null,0,  notee,commentaire,new Date(),l,j,c);
            notte.save(e);

        } else {
            notte.findById(m.getId())
                    .map(p->{
                        p.setValider((Boolean) notee);
                        p.setCommentaires(commentaire);
                        return  notte.save(p);
                    });

        }
    }
    @GetMapping("/etatetudiant/{n}")
    public int etatetudiant(@PathVariable int n) { // la méthode (findAll) pour la lecture ou liste des postulants;
        //formation f=format.findByNom(n);
        // user.infoetudiant(n);
        etudiant.findById(n)
                .map(p->{
                    p.setLu(true);

                    return  etudiant.save(p);
                });
        return 0;
    }

    @GetMapping("/infomoyennecretere/{n}")
    public List<Object> infocriterenoteparetudiantlamoyenne(@PathVariable int n) { // la méthode (findAll) pour la lecture ou liste des postulants;
        //formation f=format.findByNom(n);
        return user.criteresparusernote(n);}
    @GetMapping("/infomoyenneordonneedesetudiants/{n}")
    public List<Object[]> infocalculenoteordonnee(@PathVariable int n) { // la méthode (findAll) pour la lecture ou liste des postulants;
        //formation f=format.findByNom(n);
        return user.lesnotesetudiantparordre(n);}

    @GetMapping("/infomoyenneetudiant/{n}")
    public Object infocalculenoteetudiantparid(@PathVariable int n) { // la méthode (findAll) pour la lecture ou liste des postulants;
        //formation f=format.findByNom(n);
        return user.laanotesetudiant(n);}

    @GetMapping("/lasoutenancequiacommencere/{n}")
    public List<Object>  lasoutenancequiacommenceretpasfini(@PathVariable int n) { // la méthode (findAll) pour la lecture ou liste des postulants;
        //formation f=format.findByNom(n);
        return user.lasoutenancequiacommenceretpasencorefini(n);}

    @GetMapping("/lasoutenancequiacommencereouetudiantlu/{n}")
    public List<Object>  lasoutenancequiacommenceretpasfiniouetudiantlu(@PathVariable int n) { // la méthode (findAll) pour la lecture ou liste des postulants;
        //formation f=format.findByNom(n);
        return user.lasoutenancequiacommenceretpasencorefiniouetudiantnote(n);}
    @GetMapping("/lasout")
    public Object  lasoutenancequiacommenceretpasfini() { // la méthode (findAll) pour la lecture ou liste des postulants;
        //formation f=format.findByNom(n);
        return user.laanotesetu(LocalTime.now());}

    @GetMapping("/historiquesoutenance/{n}")
    public List<Object>historiquesoutenance(@PathVariable int n) { // la méthode (findAll) pour la lecture ou liste des postulants;
        //formation f=format.findByNom(n);
        return user.historiquesoutenance(n);}

    @GetMapping("/prochaine/{idJury}")
    public ResponseEntity<soutenance> getProchaineSoutenance(@PathVariable int idJury) {
        soutenance soutenance = service.getProchaineSoutenance(idJury);
        if (soutenance != null) {
            return ResponseEntity.ok(soutenance);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("/prochainedatesout/{idJury}")
    public String getProchai(@PathVariable int idJury) {
        long timeUntilNextSoutenance = user.soutenancerestantpourjury(idJury);
        long days = timeUntilNextSoutenance / 86400;
        long hours = (timeUntilNextSoutenance % 86400) / 3600;
        long minutes = (timeUntilNextSoutenance % 3600) / 60;
        long seconds = timeUntilNextSoutenance % 60;
        return String.format("%d jour(s) %d heure(s) %d minute(s) %d seconde(s)", days, hours, minutes, seconds);
    }
    @GetMapping("/juride/{n}")
    public  List<soutenance>  juride(@PathVariable int n) { // la méthode (findAll) pour la lecture ou liste des postulants;
        jury j=jur.findByIde(n);
        return sout.findByJurysAndDatedebutLessThanEqualAndHeuredebLessThanEqualAndHeurefinGreaterThanEqual(j,new Date(),LocalTime.now(),LocalTime.now());
    }
    @GetMapping("/time-to-next-defense/{juryId}")
    public String getTimeToNextDefense(@PathVariable int juryId) {
        // Récupérez la soutenance correspondant à l'ID du jury
        soutenance soutenance = sout.findById(juryId).orElseThrow(() -> new RuntimeException("Soutenance introuvable"));
        // soutenance soutenance =user.soutenancereslaplusprochepourjury(juryId);
        LocalTime currentTime = LocalTime.now();
        Object l= user.soutenancereslaplusprochepourjury(juryId);
        LocalTime defenseStartTime = soutenance.getHeuredeb();
        LocalTime defenseEndTime = soutenance.getHeurefin();

        // Calculez la durée restante jusqu'au début de la soutenance
        Duration durationToStart = Duration.between(currentTime, defenseStartTime);

        // Calculez la durée restante jusqu'à la fin de la soutenance
        Duration durationToEnd = Duration.between(currentTime, defenseEndTime);

        // Retournez le temps restant sous forme de chaîne de caractères
        return "Temps restant avant le début de la soutenance : " + durationToStart.toString() + "\n" +
                "Temps restant avant la fin de la soutenance : " + durationToEnd.toString();
    }

    @GetMapping("/allerrr/{n}")
    public  String  jurd(@PathVariable int n) { // la méthode (findAll) pour la lecture ou liste des postulants;
//        LocalDateTime now = LocalDateTime.now();
//
//
//            LocalDateTime start = LocalDateTime.of(jury.getDatedebut(), jury.getHeuredeb());
//            Duration duration = Duration.between(now, start);
//            long days = duration.toDays();
//            duration = duration.minusDays(days);
//            long hours = duration.toHours();
//            duration = duration.minusHours(hours);
//            long minutes = duration.toMinutes();
//
//            return ("Il vous reste " + days + "j " + hours + "h " + minutes + "mn avant votre soutenance");
//         }

        return "coucou";}

    @GetMapping("/jurideeee/{n}")
    public  Object  soutenanceprochepourjury(@PathVariable int n) { // la méthode (findAll) pour la lecture ou liste des postulants;
        jury j=jur.findByIde(n);
        return user.soutenancereslaplusprochepourjury(n);
    }
    @GetMapping("/jural/{juryId}")
    public Object fait( @PathVariable int juryId){
        String m="pas de soutenance";
        Object[] defense = (Object[]) user.soutenancereslaplusprochepourjury(juryId);
        if (defense==null){
            return "{\"message\":\"pas de soutenance prevu pour maintenant\"}";
        }
        else {
            Date defenseDate = (Date) defense[3];
            Time defenseStartTime = (Time) defense[4];

            LocalDateTime defenseStartDateTime = LocalDateTime.of(
                    defenseDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate(),
                    defenseStartTime.toLocalTime()
            );
            LocalDateTime now = LocalDateTime.now();

            Duration duration = Duration.between(now, defenseStartDateTime);
            long minutes = duration.toMinutes();
            long hours = minutes / 60;
            long days = hours / 24;
            minutes = minutes % 60;
            hours = hours % 24;
            return "{\"days\":" + days + ", \"hours\":" + hours + ", \"minutes\":" + minutes + "}";


        }
    }


    public String formatDuration(Duration duration) {
        long minutes = duration.toMinutes();
        long hours = minutes / 60;
        long days = hours / 24;
        minutes = minutes % 60;
        hours = hours % 24;

        return String.format("%dj %dh %dmn", days, hours, minutes);
    }
    public boolean isJuryAvailable(jury  jur, Date dateDebut, LocalTime heureDeb, LocalTime heureFin) {
        for (soutenance s : sout.findAll()) {
            for (jury j : s.getJurys()) {
                if (j.getIde() == jur.getIde()) {
                    LocalDateTime soutenanceStart = LocalDateTime.of(s.getDatedebut().toInstant().atZone(ZoneId.systemDefault()).toLocalDate(), s.getHeuredeb());
                    LocalDateTime soutenanceEnd = LocalDateTime.of(s.getDatedebut().toInstant().atZone(ZoneId.systemDefault()).toLocalDate(), s.getHeurefin());
                    LocalDateTime newSoutenanceStart = LocalDateTime.of(dateDebut.toInstant().atZone(ZoneId.systemDefault()).toLocalDate(), heureDeb);
                    LocalDateTime newSoutenanceEnd = LocalDateTime.of(dateDebut.toInstant().atZone(ZoneId.systemDefault()).toLocalDate(), heureFin);
                    if ((newSoutenanceStart.isBefore(soutenanceEnd) && newSoutenanceStart.isAfter(soutenanceStart)) || (newSoutenanceEnd.isBefore(soutenanceEnd) && newSoutenanceEnd.isAfter(soutenanceStart))) {
                        return false;
                    }
                }
            }
        }
        return true;
    }
    @GetMapping("/resultatautrpkiju/{juryId}/{cri}")
    public int resultatautrer( @PathVariable int juryId,@PathVariable int cri){
        Etudiant n=etudiant.findByIde(juryId);
        criteresevaluation b=critere.findByIdo(cri);
        int t=0;
        //  int m=user.lesjurysparrapportaletudiant(juryId);
        int a=0;
        List<lesnotes> l= notte.findByEtudiantAndCriteres(n,b);
        for (lesnotes note : l){
            if (note.getValider()){
                a++;
            }
            else {
                t++;
            }
        }
        if (t>a){
            return 0;
        }
        else {
            return 1;
        }
    }
    @GetMapping("/jurp/{n}")
    public  Object  toutnotes(@PathVariable int n) { // la méthode (findAll) pour la lecture ou liste des postulants;
        Etudiant o = etudiant.findByIde(n);
        int q =user.countetudiantnombrecritere(n);
        int p=0;
        List<lesnotes> a = notte.findByEtudiant(o);
        for (lesnotes note : a) {
            System.out.println("le ppppppppppppp1");
            System.out.println(p);

            criteresevaluation w=note.getCriteres();

            System.out.println("le ppppppppppppp2");
            System.out.println(p);
            if (resultatautrer(o.getIde(),w.getIdo()) == 1) {
                p++;
            }

        }
        System.out.println("le ppppppppppppp3");
        System.out.println(p);
        if(p>=q){
            return"valider";
        }
        else {
            return"pas valider";
        }}


    @GetMapping("/student/{id}")
    public String hasStudentPassedAllCriteria(@PathVariable("id") int studentId) {
        Etudiant student = etudiant.findByIde(studentId);
        List<lesnotes> studentNotes = notte.findByEtudiant(student);
        Object[] h=user.idcritereparetudiant(studentId);
        int totalCriteriaCount = user.countetudiantnombrecritere(studentId);
        int passedCriteriaCount = 0;
        for (Object note : h) {
//            criteresevaluation criteria = note.getCriteres();
            if (resultatautre(studentId, (Integer) note) == 1) {
                passedCriteriaCount= passedCriteriaCount+resultatautre(studentId, (Integer) note);

                System.out.println("le resultat");
                System.out.println(passedCriteriaCount);
            }
        }
        if (passedCriteriaCount >= totalCriteriaCount) {
            return "Validé";
        } else {
            return "Non validé";
        }
    }


    @GetMapping("/resultatau/{juryId}/{cri}")
    public int resultatautre(@PathVariable("juryId") int juryId, @PathVariable("cri") int criteriaId) {
        Etudiant student = etudiant.findByIde(juryId);
        criteresevaluation criteria = critere.findByIdo(criteriaId);
        List<lesnotes> notes = notte.findByEtudiantAndCriteres(student, criteria);
        int passedCount = 0;
        int failedCount = 0;
        for (lesnotes note : notes) {
            if (note.getValider()) {
                passedCount++;
            } else {
                failedCount++;
            }
        }
        if (failedCount > passedCount) {
            return 0;
        } else {
            return 1;
        }
    }
    @GetMapping("/trouverparnom/{n}")
    public  Object  utilisateurparnom(@PathVariable String n) { // la méthode (findAll) pour la lecture ou liste des postulants;
        //  jury j=jur.findByIde(n);
        utilisateur u=   user.findByNom(n);
        return u.getIde();
    }
    @GetMapping("/soutenanceactuelstatut/{n}")
    public  boolean  soutenanceactuelstatut(@PathVariable int n) { // la méthode (findAll) pour la lecture ou liste des postulants;
        //  jury j=jur.findByIde(n);
        // utilisateur u=   user.findByNom(n);
        return user.soutenanceencoursetat(n);
    }
    @GetMapping("/soutenanceactuelstatutts/{n}")
    public  boolean  soutenanceactuelstatutt(@PathVariable int n) { // la méthode (findAll) pour la lecture ou liste des postulants;
        //  jury j=jur.findByIde(n);
        // utilisateur u=   user.findByNom(n);
        if(user.soutenanceencours(n)==null)    {
            return false;
        }else {
            return true;
        }
    }

    @GetMapping("/resultatspp/{juryId}")
    public Map<Integer, Integer> resultat(@PathVariable("juryId") int juryId) {
        Etudiant student = etudiant.findByIde(juryId);
        List<criteresevaluation> criteres = critere.findAll();
        // récupérer tous les critères

        Map<Integer, Integer> resultats = new HashMap<>();

        for (criteresevaluation critere : criteres) {
            List<lesnotes> notes = notte.findByEtudiantAndCriteres(student, critere);
            int passedCount = 0;
            int failedCount = 0;
            for (lesnotes note : notes) {
                if (note.getValider()) {
                    passedCount++;
                } else {
                    failedCount++;
                }
            }
            if (failedCount > passedCount) {
                resultats.put(critere.getIdo(), 0);
            } else {
                resultats.put(critere.getIdo(), 1);
            }
        }

        return resultats;
    }

    @GetMapping("/resultatssst/{juryId}")
    public Map<String, String> resultattt(@PathVariable("juryId") int juryId) {
        Etudiant student = etudiant.findByIde(juryId);
        List<lesnotes> studentNotes = notte.findByEtudiant(student);
        Object[] h=user.idcritereparetudiant(juryId);
        Map<String, String> resultats = new HashMap<>();
        // int totalCriteriaCount = user.countetudiantnombrecritere(juryId);
        int passedCriteriaCount = 0;
        for (Object notef : h) {
            criteresevaluation l=critere.findByIdo((Integer) notef);
            List<lesnotes> notes = notte.findByEtudiantAndCriteres(student,l);
            int passedCount = 0;
            int failedCount = 0;
            for (lesnotes note : notes) {
                if (note.getValider()) {
                    passedCount++;
                } else {
                    failedCount++;
                }
            }
            if (failedCount > passedCount) {
                resultats.put(l.getNom(),"pas valider");
            } else {
                resultats.put(l.getNom()," valider");
            }

        }
        return resultats;

    }

    @GetMapping("/soutenancestatutpourunetudiant/{n}")
    public  boolean  soutenancestatutpourunetudiant(@PathVariable int n) { // la méthode (findAll) pour la lecture ou liste des postulants;
        //  jury j=jur.findByIde(n);
        // utilisateur u=   user.findByNom(n);
        return user.soutenancestatutpourunetudiant(n);
    }

//    @GetMapping("/listeetudiantparsoutenance/{n}")
//    public Object[] listeetudiantparsoutenancee(@PathVariable int n) { // la méthode (findAll) pour la lecture ou liste des postulants;
//        //formation f=format.findByNom(n);
//        return user.listeetudiantparsoutenance(n);}
//
//    @GetMapping("/envoiemail/{envoie}/{destinateur}/{body}/{subject}")
//    public Object envoiemail(@PathVariable String envoie,@PathVariable String destinateur,@PathVariable String body,@PathVariable String subject) { // la méthode (findAll) pour la lecture ou liste des postulants;
//        //formation f=format.findByNom(n);
//          senderService.sendSimpleEmail(envoie,destinateur,
//				body,
//				subject);
//          notification n=new notification(null,subject,body,new Date(),envoie,destinateur,false);
//          reponotif.save(n);
//        return "bien envoyer";
//        }
//    @GetMapping("/{expediteur}/{destinateur}")
//    public List<notification> getNotificationsByExpediteurAndDestinateur(@PathVariable String expediteur, @PathVariable String destinateur) {
//        return service.getNotificationsByExpediteurAndDestinateur(expediteur, destinateur);
//    }
}