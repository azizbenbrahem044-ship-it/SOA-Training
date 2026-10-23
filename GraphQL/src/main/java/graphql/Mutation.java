package graphql;

import business.ModuleBusiness;
import business.UniteEnseignementBusiness;
import com.coxautodev.graphql.tools.GraphQLRootResolver;
import entities.Module;
import entities.Module.TypeModule;
import entities.UniteEnseignement;

public class Mutation implements GraphQLRootResolver {

    private final ModuleBusiness helper = new ModuleBusiness();
    private final UniteEnseignementBusiness ueHelper = new UniteEnseignementBusiness();

    // ----- Modules -----
    public boolean addModule(String matricule, String libelle, int coef,
                             int volumeHoraire, TypeModule type, int codeUE) {
        UniteEnseignement ue = ueHelper.getUEByCode(codeUE);
        if (ue == null) return false;
        if (helper.getModuleByMatricule(matricule) != null) return false;
        return helper.addModule(new Module(matricule, libelle, coef, volumeHoraire, type, ue));
    }

    public Module updateModule(String matricule, String libelle, int coef,
                               int volumeHoraire, TypeModule type, int codeUE) {
        if (helper.getModuleByMatricule(matricule) == null) return null;
        UniteEnseignement ue = ueHelper.getUEByCode(codeUE);
        if (ue == null) return null;
        Module updated = new Module(matricule, libelle, coef, volumeHoraire, type, ue);
        return helper.updateModule(matricule, updated) ? updated : null;
    }

    public String deleteModule(String matricule) {
        return helper.deleteModule(matricule) ? "deleted successfully" : "module not found";
    }

    // ----- Unités d'Enseignement -----
    public boolean addUniteEnseignement(int code, String domaine, String responsable,
                                        int credits, int semestre) {
        if (ueHelper.getUEByCode(code) != null) return false;   // code déjà utilisé
        return ueHelper.addUniteEnseignement(
                new UniteEnseignement(code, domaine, responsable, credits, semestre));
    }

    public boolean updateUniteEnseignement(int code, String domaine, String responsable,
                                           int credits, int semestre) {
        UniteEnseignement updated =
                new UniteEnseignement(code, domaine, responsable, credits, semestre);
        return ueHelper.updateUniteEnseignement(code, updated);
    }

    public boolean deleteUniteEnseignement(int code) {
        return ueHelper.deleteUniteEnseignement(code);
    }
}