package com.pharmacy.bulk;

import com.pharmacy.model.*;
import com.pharmacy.service.*;
import lombok.AllArgsConstructor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
@AllArgsConstructor
public class BulkInsertProducts {

    private final IManufacturerService iManufacturerService;
    private final IProductTypeService iProductTypeService;
    private final ISupplierService iSupplierService;
    private final IProductService iProductService;
    private final ITaxService iTaxService;

    private Map<String, Manufacturer> manufacturerMap = new ConcurrentHashMap<>();
    private Map<String, Supplier> supplierMap = new ConcurrentHashMap<>();
    private Map<String, ProductType> productTypeMap = new ConcurrentHashMap<>();

    public void insert() {
        ExecutorService executorService = Executors.newFixedThreadPool(8);
        Tax tax12 = new Tax();
        tax12.setName(ETax.GST);
        tax12.setValue(12.0);
        Thread medicine1 = new Thread(new Medicine1(iTaxService.saveTax(tax12)));
        Tax tax18 = new Tax();
        tax18.setName(ETax.GST);
        tax18.setValue(18.0);
        Thread medicine2 = new Thread(new Medicine1(iTaxService.saveTax(tax18)));
        executorService.submit(medicine1);
        executorService.submit(medicine2);
        executorService.shutdown();
    }

    @AllArgsConstructor
    class Medicine1 implements Runnable {
        private Tax tax;

        @Override
        public void run() {
            System.out.println(LocalDateTime.now());
            Random rand = new Random();
            String path = "C:\\pharmacy\\pharmacy_backend\\database\\medicine1.xlsx";

            try (FileInputStream file = new FileInputStream(new File(path))) {

                List<String> productTypes = new ArrayList<>();
                getProductTypes(productTypes);

                List<EShelf> shelves = new ArrayList<>();
                getShelves(shelves);

                List<Boolean> booleans = getBooleans();

                List<String> dosage = getDosage();

                Workbook workbook = new XSSFWorkbook(file);
                Sheet sheet = workbook.getSheetAt(0);
                List<Product> products = new ArrayList<>();
                for (Row row : sheet) {
                    if (row.getRowNum() != 0) {
                        Product product = new Product();
                        product.setName(row.getCell(0).getStringCellValue());
                        product.setManufacturer(getManufacturer(row.getCell(2).getStringCellValue()));
                        Cell gen1 = row.getCell(3);
                        Cell gen2 = row.getCell(4);
                        Cell gen3 = row.getCell(5);
                        String gen1Name = gen1 != null ? gen1.getStringCellValue() : "";
                        String gen2Name = gen2 != null ? gen2.getStringCellValue() : "";
                        String gen3Name = gen3 != null ? gen3.getStringCellValue() : "";
                        product.setGenericName(gen1Name + " " + gen2Name + " " + gen3Name);
                        Cell desc = row.getCell(6);
                        product.setDescription(desc != null ? desc.getStringCellValue() : "");
                        Cell sideEffect = row.getCell(7);
                        product.setSideEffects(sideEffect != null ? sideEffect.getStringCellValue() : "");
                        product.setProductType(getProductType(productTypes.get(rand.nextInt(11))));
                        product.setSuppliers(getSuppliers(row.getCell(2).getStringCellValue()));
                        double priceDouble = row.getCell(1).getNumericCellValue();
                        Stock stock1 = getStock1(rand, shelves, booleans, dosage, priceDouble, tax);
                        stock1.setProduct(product);
                        Stock stock2 = getStock2(rand, shelves, booleans, dosage, priceDouble, tax);
                        stock2.setProduct(product);
                        products.add(product);
                    }
                }

                iProductService.saveProducts(products);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                System.out.println(LocalDateTime.now());
            }
        }
    }

    private static List<String> getDosage() {
        return List.of("250mg", "500mg", "100ml", "500ml", "0.25mg", "0.5mg", "1mg");
    }

    private static List<Boolean> getBooleans() {
        return List.of(true, false);
    }

    private static void getShelves(List<EShelf> shelves) {
        EShelf[] values = EShelf.values();
        for (EShelf shelf : values) {
            shelves.add(shelf);
        }
    }

    private static void getProductTypes(List<String> productTypes) {
        ECategory[] categories = ECategory.values();
        for (ECategory ECategory : categories) {
            productTypes.add(ECategory.name());
        }
    }

    @AllArgsConstructor
    class Medicine2 implements Runnable {
        private Tax tax;

        @Override
        public void run() {
            System.out.println(LocalDateTime.now());
            Random rand = new Random();
            String path = "C:\\pharmacy\\pharmacy_backend\\database\\medicine2.xlsx";

            try (FileInputStream file = new FileInputStream(new File(path))) {

                List<String> productTypes = new ArrayList<>();
                getProductTypes(productTypes);

                List<EShelf> shelves = new ArrayList<>();
                getShelves(shelves);

                List<String> dosage = getDosage();

                List<Boolean> booleans = getBooleans();

                Workbook workbook = new XSSFWorkbook(file);
                Sheet sheet = workbook.getSheetAt(0);
                List<Product> products = new ArrayList<>();
                for (Row row : sheet) {
                    if (row.getRowNum() != 0) {
                        Product product = new Product();
                        product.setName(row.getCell(1).getStringCellValue());
                        product.setManufacturer(getManufacturer(row.getCell(4).getStringCellValue()));
                        Cell gen = row.getCell(2);
                        product.setGenericName(gen != null ? gen.getStringCellValue() : "");
                        Cell desc = row.getCell(5);
                        product.setDescription(desc != null ? desc.getStringCellValue() : "");
                        Cell sideEffect = row.getCell(6);
                        product.setSideEffects(sideEffect != null ? sideEffect.getStringCellValue() : "");
                        product.setProductType(getProductType(productTypes.get(rand.nextInt(11))));
                        product.setSuppliers(getSuppliers(row.getCell(4).getStringCellValue()));
                        double priceDouble = row.getCell(3).getNumericCellValue();
                        Stock stock1 = getStock1(rand, shelves, booleans, dosage, priceDouble, tax);
                        stock1.setProduct(product);
                        Stock stock2 = getStock2(rand, shelves, booleans, dosage, priceDouble, tax);
                        stock2.setProduct(product);
                        products.add(product);
                    }
                }

                iProductService.saveProducts(products);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                System.out.println(LocalDateTime.now());
            }
        }
    }

    private Stock getStock2(Random rand, List<EShelf> shelves, List<Boolean> booleans, List<String> dosage, double priceDouble, Tax tax) {
        Stock stock2 = new Stock();
        stock2.setTax(tax);
        stock2.setInStock(true);
        stock2.setDosage(dosage.get(rand.nextInt(7)));
        stock2.setQuantity(rand.nextInt((100 - 1) + 1));
        stock2.setExpiryDate(LocalDate.now().plusMonths(rand.nextInt(10)));
        stock2.setManufactureDate(LocalDate.now().minusMonths(rand.nextInt(10)));
        stock2.setBatchNumber(UUID.randomUUID().toString());
        stock2.setBaseRate(priceDouble);
        stock2.setPurchaseRate(((stock2.getTax().getValue() / 100) * stock2.getBaseRate()) + stock2.getBaseRate());
        stock2.setSaleRate((0.2 * stock2.getPurchaseRate()) + stock2.getPurchaseRate());
        stock2.setProfit(stock2.getSaleRate() - stock2.getPurchaseRate());
        stock2.setShelf(shelves.get(rand.nextInt(100)));
        stock2.setIsDiscontinued(booleans.get(rand.nextInt(2)));
        return stock2;
    }

    private Stock getStock1(Random rand, List<EShelf> shelves, List<Boolean> booleans, List<String> dosage, double priceDouble, Tax tax) {
        Stock stock1 = new Stock();
        stock1.setTax(tax);
        stock1.setInStock(booleans.get(rand.nextInt(2)));
        stock1.setDosage(dosage.get(rand.nextInt(7)));
        stock1.setQuantity(rand.nextInt(100));
        stock1.setExpiryDate(LocalDate.now().plusMonths(rand.nextInt(10)));
        stock1.setManufactureDate(LocalDate.now().minusMonths(rand.nextInt(10)));
        stock1.setBatchNumber(UUID.randomUUID().toString());
        stock1.setBaseRate(priceDouble);
        stock1.setPurchaseRate(((stock1.getTax().getValue() / 100) * stock1.getBaseRate()) + stock1.getBaseRate());
        stock1.setSaleRate((0.2 * stock1.getPurchaseRate()) + stock1.getPurchaseRate());
        stock1.setProfit(stock1.getSaleRate() - stock1.getPurchaseRate());
        stock1.setShelf(shelves.get(rand.nextInt(100)));
        stock1.setIsDiscontinued(booleans.get(rand.nextInt(2)));
        return stock1;
    }

    private List<Supplier> getSuppliers(String supplierName) {
        Supplier supplier = null;
        if (supplierMap.containsKey(supplierName)) {
            supplier = supplierMap.get(supplierName);
        } else {
            supplier = new Supplier();
            supplier.setActive(true);
            supplier.setName(supplierName);
            supplier.setGstin(UUID.randomUUID().toString());
            supplier.setLicenseNumber(UUID.randomUUID().toString());

            ContactDetail contactDetail = new ContactDetail();
            contactDetail.setName("Ashay Kolhe");
            contactDetail.setMobile1("9766750554");
            contactDetail.setMobileCountryCode("91");

            supplier.setContactDetail(contactDetail);
            supplierMap.put(supplierName, iSupplierService.saveSupplier(supplier));
        }
        return List.of(supplier);
    }

    private ProductType getProductType(String productTypeName) {
        ProductType productType = null;
        if (productTypeMap.containsKey(productTypeName)) {
            productType = productTypeMap.get(productTypeName);
        } else {
            productType = new ProductType();
            productType.setName(productTypeName);
            productTypeMap.put(productTypeName, iProductTypeService.saveProductType(productType));
        }
        return productType;
    }

    private Manufacturer getManufacturer(String manufacturerName) {
        Manufacturer manufacturer = null;

        if (manufacturerMap.containsKey(manufacturerName)) {
            manufacturer = manufacturerMap.get(manufacturerName);
        } else {
            manufacturer = new Manufacturer();
            manufacturer.setName(manufacturerName);
            manufacturer.setLicenseNumber(UUID.randomUUID().toString());
            manufacturer.setGstin(UUID.randomUUID().toString());

            ContactDetail contactDetail = new ContactDetail();
            contactDetail.setName("Ashay Kolhe");
            contactDetail.setMobile1("9766750554");
            contactDetail.setMobileCountryCode("91");

            manufacturer.setContactDetail(contactDetail);
            manufacturerMap.put(manufacturerName, iManufacturerService.saveManufacturer(manufacturer));
        }
        return manufacturer;
    }
}
