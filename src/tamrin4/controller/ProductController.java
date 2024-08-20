package tamrin4.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import tamrin4.model.da.ProductDa;
import tamrin4.model.entity.Brand;
import tamrin4.model.entity.Product;
import tamrin4.model.utils.Validation;

import java.net.URL;
import java.util.List;
import java.util.Observable;
import java.util.ResourceBundle;

public class ProductController implements Initializable {
    private final Validation validation = new Validation();

    @FXML
    private TextField idTxt , nameTxt , priceTxt , countTxt ;

    @FXML
    private ComboBox<String> brandCmb;

    @FXML
    private Button saveBtn , editBtn , removeBtn ;

    @FXML
    private TableView<Product> pInfo;

    @FXML
    private TableColumn<Product , Integer> idCol , countCol ;

    @FXML TableColumn<Product , Double> priceCol ;

    @FXML
    private TableColumn<Product , String> nameCol ;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        for (Brand brand : Brand.values()){
            brandCmb.getItems().add(brand.name());
        }

        resetForm();

        saveBtn.setOnAction(event -> {
            try (ProductDa productDa = new ProductDa()) {
            Product product = Product
                    .builder()
                    .name(validation.nameValidator(nameTxt.getText()))
                    .brand(Brand.valueOf(brandCmb.getSelectionModel().getSelectedItem()))
                    .price(Double.parseDouble(priceTxt.getText()))
                    .count(Integer.parseInt(countTxt.getText()))
                    .build();
            productDa.save(product);

            Alert alert = new Alert(Alert.AlertType.INFORMATION,"Product Saved\n" +product.toString());
            alert.show();
            resetForm();
            } catch (Exception e){
                Alert alert = new Alert(Alert.AlertType.ERROR,"Product save Error\n" + e.getMessage());
                alert.show();
            }
        });

        editBtn.setOnAction(event -> {
            try (ProductDa productDa = new ProductDa()) {
                Product product = Product
                        .builder()
                        .id(Integer.parseInt(idTxt.getText()))
                        .name(validation.nameValidator(nameTxt.getText()))
                        .brand(Brand.valueOf(brandCmb.getSelectionModel().getSelectedItem()))
                        .price(Double.parseDouble(priceTxt.getText()))
                        .count(Integer.parseInt(countTxt.getText()))
                        .build();
                productDa.edit(product);

                Alert alert = new Alert(Alert.AlertType.INFORMATION,"Product Edited\n" +product.toString());
                alert.show();
                resetForm();
            } catch (Exception e){
                Alert alert = new Alert(Alert.AlertType.ERROR,"Product Edit Error\n" + e.getMessage());
                alert.show();
            }
        });

        removeBtn.setOnAction(event -> {
            try (ProductDa productDa= new ProductDa()){
                Alert confirmAlert = new Alert(Alert.AlertType.CONFIRMATION,"Are You Sure To Remove Product ?");
                if (confirmAlert.showAndWait().get() ==ButtonType.OK){
                    int id = Integer.parseInt(idTxt.getText());
                    productDa.remove(id);
                    Alert alert = new Alert(Alert.AlertType.INFORMATION,"Product Removed With ID : " +id);
                    alert.show();
                    resetForm();
                }
            }catch (Exception e){
                Alert alert = new Alert(Alert.AlertType.ERROR, "product Remove Error\n" +e.getMessage());
                alert.show();
            }
        });


        pInfo.setOnMouseReleased(event -> {
            Product product = pInfo.getSelectionModel().getSelectedItem();
            idTxt.setText(String.valueOf(product.getId()));
            nameTxt.setText(product.getName());
            brandCmb.getSelectionModel().select(product.getBrand().name());
            priceTxt.setText(String.valueOf(product.getPrice()));
            countTxt.setText(String.valueOf(product.getCount()));
        });
    }

    private void resetForm(){
        idTxt.clear();
        nameTxt.clear();
        brandCmb.getSelectionModel().select(0);
        priceTxt.clear();
        countTxt.clear();

        try (ProductDa productDa= new ProductDa()) {
            refreshTable(productDa.findAll());
        }catch (Exception e){
            Alert alert = new Alert(Alert.AlertType.ERROR,"Find Products Error\n" + e.getMessage());
            alert.show();

        }
    }

    private void refreshTable(List<Product> productList){
        ObservableList<Product> products = FXCollections.observableList(productList);

        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        priceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
        countCol.setCellValueFactory(new PropertyValueFactory<>("count"));
        pInfo.setItems(products);
    }
}
