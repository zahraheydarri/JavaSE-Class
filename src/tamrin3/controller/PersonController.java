package tamrin3.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import tamrin3.model.da.PersonDa;
import tamrin3.model.entity.City;
import tamrin3.model.entity.Gender;
import tamrin3.model.entity.Person;
import tamrin3.model.utils.Validation;

import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

public class PersonController implements Initializable {
    private final Validation validation = new Validation();

    @FXML
    private TextField idTxt, nameTxt, familyTxt;

    @FXML
    private DatePicker birthDate;

    @FXML
    private RadioButton maleRdo, femaleRdo;

    @FXML
    private ToggleGroup genderToggle;

    @FXML
    private ComboBox<String> cityCmb;

    @FXML
    private CheckBox seChk, eeChk;

    @FXML
    private Button saveBtn, editBtn, removeBtn;

    @FXML
    private TableView<Person> personTbl;

    @FXML
    private TableColumn<Person, Integer> idCol;

    @FXML
    private TableColumn<Person, String> nameCol, familyCol, genderCol;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        for (City city : City.values()) {
            cityCmb.getItems().add(city.name());
        }

        resetForm();


        saveBtn.setOnAction(event -> {
            try (PersonDa personDa = new PersonDa()) {
                RadioButton selectedRdo = (RadioButton) genderToggle.getSelectedToggle();
                Person person =
                        Person
                                .builder()
                                .name(validation.nameValidator(nameTxt.getText()))
                                .family(validation.familyValidator(familyTxt.getText()))
                                .gender(Gender.valueOf(selectedRdo.getText()))
                                .birthDate(birthDate.getValue())
                                .city(City.valueOf(cityCmb.getSelectionModel().getSelectedItem()))
                                .seSkill(seChk.isSelected())
                                .eeSkill(eeChk.isSelected())
                                .build();
                personDa.save(person);

                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Person Saved\n" + person.toString());
                alert.show();
                resetForm();
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Person Save Error\n" + e.getMessage());
                alert.show();
            }
        });

        editBtn.setOnAction(event -> {
            try (PersonDa personDa = new PersonDa()) {
                // Data Validation
                RadioButton selectedRdo = (RadioButton) genderToggle.getSelectedToggle();
                Person person =
                        Person
                                .builder()
                                .id(Integer.parseInt(idTxt.getText()))
                                .name(validation.nameValidator(nameTxt.getText()))
                                .family(validation.familyValidator(familyTxt.getText()))
                                .gender(Gender.valueOf(selectedRdo.getText()))
                                .birthDate(birthDate.getValue())
                                .city(City.valueOf(cityCmb.getSelectionModel().getSelectedItem()))
                                .seSkill(seChk.isSelected())
                                .eeSkill(eeChk.isSelected())
                                .build();
                personDa.edit(person);

                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Person Edited\n" + person.toString());
                alert.show();
                resetForm();
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Person Edit Error\n" + e.getMessage());
                alert.show();
            }
        });

        removeBtn.setOnAction(event -> {
            try (PersonDa personDa = new PersonDa()) {
                Alert confirmAlert = new Alert(Alert.AlertType.CONFIRMATION, "Are You Sure To Remove Person ?");
                if (confirmAlert.showAndWait().get() == ButtonType.OK) {
                    int id = Integer.parseInt(idTxt.getText());
                    personDa.remove(id);
                    Alert alert = new Alert(Alert.AlertType.INFORMATION, "Person Removed With ID : " + id);
                    alert.show();
                    resetForm();
                }

            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Person Remove Error\n" + e.getMessage());
                alert.show();
            }
        });

        personTbl.setOnMouseReleased(event->{
            Person person = personTbl.getSelectionModel().getSelectedItem();
            idTxt.setText(String.valueOf(person.getId()));
            nameTxt.setText(person.getName());
            familyTxt.setText(person.getFamily());
            if(person.getGender().equals(Gender.Male)){
                maleRdo.setSelected(true);
            }else {
                femaleRdo.setSelected(true);
            }
            birthDate.setValue(person.getBirthDate());
            cityCmb.getSelectionModel().select(person.getCity().name());
            seChk.setSelected(person.isSeSkill());
            eeChk.setSelected(person.isEeSkill());
        });
    }

    private void resetForm() {
        idTxt.clear();
        nameTxt.clear();
        familyTxt.clear();

        cityCmb.getSelectionModel().select(0);
        birthDate.setValue(LocalDate.now());

        maleRdo.setSelected(true);

        seChk.setSelected(false);
        eeChk.setSelected(false);

        try (PersonDa personDa = new PersonDa()) {
            refreshTable(personDa.findAll());
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Find Persons Error\n" + e.getMessage());
            alert.show();
        }
    }

    private void refreshTable(List<Person> personList) {
        ObservableList<Person> persons = FXCollections.observableList(personList);

        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        familyCol.setCellValueFactory(new PropertyValueFactory<>("family"));
        genderCol.setCellValueFactory(new PropertyValueFactory<>("gender"));

        personTbl.setItems(persons);
    }
}

