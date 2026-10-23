package graphql;

import business.ModuleBusiness;
import business.UniteEnseignementBusiness;
import com.coxautodev.graphql.tools.GraphQLRootResolver;
import entities.Module;
import entities.Module.TypeModule;
import entities.UniteEnseignement;

import java.util.List;

public class Query implements GraphQLRootResolver {

    private final ModuleBusiness helper = new ModuleBusiness();
    private final UniteEnseignementBusiness ueHelper = new UniteEnseignementBusiness();

    // ----- Modules -----
    public List<Module> allModules() {
        return helper.getAllModules();
    }

    public Module getModuleByMatricule(String matricule) {
        return helper.getModuleByMatricule(matricule);
    }

    public List<Module> getModulesByType(TypeModule type) {
        return helper.getModulesByType(type);
    }

    // ----- Unités d'Enseignement -----
    public List<UniteEnseignement> allUEs() {
        return ueHelper.getListeUE();
    }

    public UniteEnseignement getUEByCode(int code) {
        return ueHelper.getUEByCode(code);
    }

    public List<UniteEnseignement> getUEByDomaine(String domaine) {
        return ueHelper.getUEByDomaine(domaine);
    }

    public List<UniteEnseignement> getUEBySemestre(int semestre) {
        return ueHelper.getUEBySemestre(semestre);
    }
}