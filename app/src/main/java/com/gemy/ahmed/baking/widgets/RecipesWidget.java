package com.gemy.ahmed.baking.widgets;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.RemoteViews;

import com.gemy.ahmed.baking.R;
import com.gemy.ahmed.baking.models.Ingredient;
import com.gemy.ahmed.baking.models.Recipe;

import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of App Widget functionality.
 */
public class RecipesWidget extends AppWidgetProvider {

    List<Ingredient> ingredients = new ArrayList<>();
    private static final String TAG = "WidgetService";


    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        for (int appWidgetId : appWidgetIds) {
            Log.d(TAG, "updateAppWidget: " + appWidgetId + "  " + ingredients.size());

            RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.recipes_widget);
            views.removeAllViews(R.id.appwidget_container);
            for (Ingredient ingredient : ingredients) {
                Log.d(TAG, "onUpdate: " + ingredient.getIngredient());
                RemoteViews row = new RemoteViews(context.getPackageName(), R.layout.ingredient_item);
                String q = String.valueOf(ingredient.getQuantity());
                String name = ingredient.getIngredient();
                String measure = ingredient.getMeasure();
                row.setTextViewText(R.id.tv_ingredient_type, name);
                row.setTextViewText(R.id.tv_ingredient_amount, measure);
                row.setTextViewText(R.id.tv_ingredient_amount_type, q);

                views.addView(R.id.appwidget_container, row);
            }
//            views.setEmptyView(R.id.appwidget_container, R.id.recipe_empty);

            appWidgetManager.updateAppWidget(appWidgetId, views);
        }
    }

    @Override
    public void onReceive(Context context, Intent intent) {

        if (intent.hasExtra("b")) {
            Bundle bundle = intent.getBundleExtra("b");

            Recipe recipe = bundle.getParcelable("recipe");
            if (recipe != null) {
                ingredients = recipe.getIngredients();
            }
            AppWidgetManager manager = AppWidgetManager.getInstance(context);
            ComponentName componentName = new ComponentName(context, RecipesWidget.class);
            Log.d(TAG, "onReceive: " + manager.getAppWidgetIds(componentName)[0]);
            this.onUpdate(context, manager, manager.getAppWidgetIds(componentName));
        } else
            super.onReceive(context, intent);

    }

}

