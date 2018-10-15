package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.nio.file.Path;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import javafx.collections.ObservableList;
import seedu.address.logic.CommandHistory;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.ModuleList;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyModuleList;
import seedu.address.model.credential.Credential;
import seedu.address.model.credential.ReadOnlyCredentialStore;
import seedu.address.model.module.Module;
import seedu.address.model.person.Person;
import seedu.address.model.user.Admin;
import seedu.address.model.user.Role;
import seedu.address.model.user.User;
import seedu.address.testutil.AdminBuilder;
import seedu.address.testutil.ModuleBuilder;

public class RemoveModuleFromDatabaseCommandTest {

    private static final String ABSENT_MODULE = "CS9999";

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    private CommandHistory commandHistory = new CommandHistory();


    @Test
    public void constructor_nullModule_throwsNullPointerException() {
        thrown.expect(NullPointerException.class);
        new RemoveModuleFromDatabaseCommand(null);
    }

    @Test
    public void notAdmin_throwsCommandException() throws Exception {
        RemoveModuleFromDatabaseCommand removeModuleFromDatabaseCommand =
                new RemoveModuleFromDatabaseCommand("CS1010");

        thrown.expect(CommandException.class);
        thrown.expectMessage(AddAdminCommand.MESSAGE_NOT_ADMIN);
        Model model = new ModelManager();
        User fakeAdmin = new AdminBuilder().withRole(Role.STUDENT).build();
        model.setCurrentUser(fakeAdmin);

        removeModuleFromDatabaseCommand.execute(model, commandHistory);
    }

    @Test
    public void execute_moduleExist_removeSuccessful() throws Exception {
        Module validModule = new ModuleBuilder().build();
        ModelStub modelStub = new ModelStubWithModule(validModule);

        CommandResult commandResult =
                new RemoveModuleFromDatabaseCommand(validModule.getCode()).execute(modelStub, commandHistory);

        assertEquals(String.format(RemoveModuleFromDatabaseCommand.MESSAGE_DELETE_MODULE_SUCCESS,
                validModule.getCode()), commandResult.feedbackToUser);
        assertFalse(modelStub.hasModuleInDatabase(validModule));
    }

    @Test
    public void execute_moduleDoesNotExist_throwsCommandException() throws Exception {
        Module validModule = new ModuleBuilder().build();
        ModelStub modelStub = new ModelStubWithModule(validModule);

        RemoveModuleFromDatabaseCommand removeModuleFromDatabaseCommand =
                new RemoveModuleFromDatabaseCommand(ABSENT_MODULE);

        thrown.expect(CommandException.class);
        thrown.expectMessage(RemoveModuleFromDatabaseCommand.MESSAGE_MODULE_NOT_FOUND);

        removeModuleFromDatabaseCommand.execute(modelStub, commandHistory);
    }

    @Test
    public void equals() {
        Module module1 = new ModuleBuilder().withCode("CS1000").build();
        Module module2 = new ModuleBuilder().withCode("CS2000").build();
        RemoveModuleFromDatabaseCommand removeModule1FromDatabaseCommand =
                new RemoveModuleFromDatabaseCommand(module1.getCode());
        RemoveModuleFromDatabaseCommand removeModule2FromDatabaseCommand =
                new RemoveModuleFromDatabaseCommand(module2.getCode());

        // same object -> returns true
        assertTrue(removeModule1FromDatabaseCommand.equals(removeModule1FromDatabaseCommand));

        // same values -> returns true
        RemoveModuleFromDatabaseCommand removeModule1CommandCopy =
                new RemoveModuleFromDatabaseCommand(module1.getCode());
        assertTrue(removeModule1CommandCopy.equals(removeModule1CommandCopy));

        // different types -> returns false
        assertFalse(removeModule1CommandCopy.equals(1));

        // null -> returns false
        assertFalse(removeModule1CommandCopy.equals(null));

        // different module -> returns false
        assertFalse(removeModule1FromDatabaseCommand.equals(removeModule2FromDatabaseCommand));
    }

    /**
     * A default model stub that have all of the methods failing.
     */
    private class ModelStub implements Model {
        @Override
        public void addPerson(Person person) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasModule(Module module) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void removeModule(Module module) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addModule(Module module) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void resetData(ReadOnlyAddressBook newData) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyAddressBook getAddressBook() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyModuleList getModuleList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasPerson(Person person) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deletePerson(Person target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updatePerson(Person target, Person editedPerson) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Person> getFilteredPersonList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredPersonList(Predicate<Person> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean canUndoAddressBook() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean canRedoAddressBook() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void undoAddressBook() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void redoAddressBook() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void commitAddressBook() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addCredential(Credential credential) {
            throw new AssertionError("This method should not be called.");

        }

        @Override
        public void addAdmin(Admin admin) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addModuleToDatabase(Module module) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void removeModuleFromDatabase(Module module) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasModuleInDatabase(Module module) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Module> getObservableModuleList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasCredential(Credential credential) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean isVerifiedCredential(Credential credential) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean isAdmin() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyCredentialStore getCredentialStore() {
            throw new AssertionError("This method should not be called.");

        }

        @Override
        public User getCurrentUser() {
            throw new AssertionError("This method should not be called.");

        }

        @Override
        public void setCurrentUser(User user) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Optional<Module> searchModuleInModuleList(Module module) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public List<Module> searchKeyWordInModuleList(Module keyword) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void saveUserFile(User user, Path savePath) {
            throw new AssertionError("This method should not be called.");
        }
    }

    /**
     * A Model stub that contains a single module.
     */
    private class ModelStubWithModule extends ModelStub {

        private ModuleList moduleList = new ModuleList();

        ModelStubWithModule(Module module) {
            requireNonNull(module);
            moduleList.addModule(module);
        }

        @Override
        public ReadOnlyModuleList getModuleList() {
            return moduleList;
        }

        @Override
        public void removeModuleFromDatabase(Module module) {
            requireNonNull(module);
            moduleList.removeModule(module);
        }

        @Override
        public boolean hasModuleInDatabase(Module module) {
            requireNonNull(module);
            return moduleList.hasModule(module);
        }

        @Override
        public ObservableList<Module> getObservableModuleList() {
            ModuleList modList = (ModuleList) this.getModuleList();
            return modList.getModuleList();
        }


        @Override
        public boolean isAdmin() {
            return true;
        }
    }
}