package pt.branden.brandenportal;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import org.junit.jupiter.api.Test;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

class ArchTest {

    @Test
    void servicesAndRepositoriesShouldNotDependOnWebLayer() {

        JavaClasses importedClasses = new ClassFileImporter()
            .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_TESTS)
            .importPackages("pt.branden.brandenportal");

        noClasses()
            .that()
                .resideInAnyPackage("pt.branden.brandenportal.service..")
            .or()
                .resideInAnyPackage("pt.branden.brandenportal.repository..")
            .should().dependOnClassesThat()
                .resideInAnyPackage("..pt.branden.brandenportal.web..")
        .because("Services and repositories should not depend on web layer")
        .check(importedClasses);
    }
}
