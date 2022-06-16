package com.example.fish_island;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fish_island.adapter.CategoryAdapter;
import com.example.fish_island.adapter.ProductAdapter;
import com.example.fish_island.adapter.StoryAdapter;
import com.example.fish_island.model.AuthToken;
import com.example.fish_island.model.BagItem;
import com.example.fish_island.model.Category;
import com.example.fish_island.model.Product;
import com.example.fish_island.model.Story;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView categoryRecycler, storyRecycler, productRecycler;
    CategoryAdapter categoryAdapter;
    StoryAdapter storyAdapter;

    Intent intent;

    static ProductAdapter productAdapter;
    static final List<Product> productList = new ArrayList<>();
    static List<Product> fullProductList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //отчистка массивов с товарами
        productList.clear();
        fullProductList.clear();

        //заполнение массива категорий
        List<Category> categoryList = new ArrayList<>();
        categoryList.add(new Category(1, "Красная рыба"));
        categoryList.add(new Category(2, "Вяленая рыба"));
        categoryList.add(new Category(3, "Копченная рыба"));
        categoryList.add(new Category(4, "Креветки и лангустины"));
        categoryList.add(new Category(5, "Икра"));
        categoryList.add(new Category(6, "Морепродукты"));
        categoryList.add(new Category(7, "Свежемороженная рыба"));
        categoryList.add(new Category(8, "Филе, фарш, котлеты"));
        categoryList.add(new Category(9, "Слабосоленая рыба"));
        categoryList.add(new Category(10, "Консервы"));
        categoryList.add(new Category(11, "Приправы"));
        categoryList.add(new Category(12, "Масла"));

        //вызов блока категорий
        setCategoryRecycler(categoryList);

        //заполнение масива с историями
        List<Story> storyList = new ArrayList<>();
        storyList.add(new Story(1,"1"));
        storyList.add(new Story(2,"2"));
        storyList.add(new Story(3,"3"));
        storyList.add(new Story(4,"4"));
        storyList.add(new Story(5,"5"));

        //вызов блока историй
        SetStoryRecycler(storyList);

        //заполнение массива с товарами
        productList.add(new Product(1,"990","product_img_2", "Семга", "Семга от 3 до 7 кг\n" + "С головой\n" + "Мурманск ", 1));
        productList.add(new Product(2,"1050","product_img", "Форель", "Форель от 2.5 до 3.5 кг\n" + "Без головы\n" + "Чили ", 2));
        productList.add(new Product(3,"2000","trout", "Форель с/c", "Форель от 2.5 до 3.5 кг\n" + "Без головы\n" + "Чили ", 9));
        productList.add(new Product(4,"520","mackerel", "Скумбрия х/к", "Форель от 2.5 до 3.5 кг\n" + "Без головы\n" + "Чили", 3));
        productList.add(new Product(5,"550","ram", "Тарань азовская вяленая", "Форель от 2.5 до 3.5 кг" , 2));
        productList.add(new Product(6,"1450","caviar", "Икра горбуши 0.25", "Форель от 2.5 до 3.5 кг\n" + "Без головы\n" + "Чили ", 5));

        //копирование массива с товарами
        fullProductList.addAll(productList);

        //вызов блока с товарами
        setProductRecycler(productList);



    }

    //Корзина
    public void openBagClick(View view){
        if (view.getId() == R.id.openBag) {
            if(BagItem.item_id.isEmpty()){
                intent = new Intent("fishIsland.intent.action.emptyBag");
            }
            else {
                intent = new Intent("fishIsland.intent.action.bag");
            }
            startActivity(intent);
        }
    }

    //Меню
    public void openNavClick(View view){
        if (view.getId() == R.id.openNav) {
            intent = new Intent("fishIsland.intent.action.nav");
            startActivity(intent);
        }
    }

    //профиль
    public void openProfileClick(View view){
        if (view.getId() == R.id.profileButton) {
            if(AuthToken.Token.isEmpty()){
                intent = new Intent("fishisland.intent.action.auth");
            }
            else {
                intent = new Intent("fishIsland.intent.action.profile");
            }
            startActivity(intent);
        }
    }

    //история
    public void openOrderHistoryClick(View view){
        if (view.getId() == R.id.historyButton) {
            intent = new Intent("fishIsland.intent.action.orderHistory");
            startActivity(intent);
        }
    }

    //избраное
    public void openFavoritesClick(View view){
        if (view.getId() == R.id.favoritesButton) {
            intent = new Intent("fishIsland.intent.action.favorite");
            startActivity(intent);
        }
    }

    //домой отменяет сортировку
    public void homeClick(View view){
        if (view.getId() == R.id.homeButton) {
            productList.clear();
            productList.addAll(fullProductList);

            productAdapter.notifyDataSetChanged();
        }
    }

    //Блок товаров
    private void setProductRecycler(List<Product> productList) {
        RecyclerView.LayoutManager layoutManager  = new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);

        productRecycler = findViewById(R.id.productRecycler);
        productRecycler.setLayoutManager(layoutManager);

        productAdapter = new ProductAdapter(this, productList);
        productRecycler.setAdapter(productAdapter);
    }

    //Блок историй
    private void SetStoryRecycler(List<Story> storyList) {
        RecyclerView.LayoutManager layoutManager  = new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);

        storyRecycler = findViewById(R.id.storyRecycler);
        storyRecycler.setLayoutManager(layoutManager);

        storyAdapter = new StoryAdapter(this, storyList);
        storyRecycler.setAdapter(storyAdapter);
    }


    //Блок категорий
    private void setCategoryRecycler(List<Category> categoryList) {

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);

        categoryRecycler = findViewById(R.id.categoryRecycler);
        categoryRecycler.setLayoutManager(layoutManager);

        categoryAdapter = new CategoryAdapter(this, categoryList);
        categoryRecycler.setAdapter(categoryAdapter);

    }

    //Сортировка
    public static void showProductByCategory(int category){

        productList.clear();
        productList.addAll(fullProductList);

        List<Product> filterProduct = new ArrayList<>();

        for(Product product : productList){
            if(product.getCategory() == category) filterProduct.add(product);
        }

        productList.clear();
        productList.addAll(filterProduct);

        productAdapter.notifyDataSetChanged();
    }

}