package com.example.testprojects.utils;

import android.app.Activity;

import androidx.appcompat.widget.Toolbar;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.WindowInsetsControllerCompat;

public class CommonUtils {
    public static void setUpEdgeToEdge(Activity activity) {
        WindowCompat.setDecorFitsSystemWindows(activity.getWindow(), false);
        WindowInsetsControllerCompat controllerCompat =
                WindowCompat.getInsetsController(activity.getWindow(),
                        activity.getWindow().getDecorView());
        controllerCompat.setAppearanceLightNavigationBars(true);
        controllerCompat.setAppearanceLightStatusBars(true);

        ViewCompat.setOnApplyWindowInsetsListener(
                activity.findViewById(android.R.id.content),
                (v, windowInsets) -> {
                    Insets insets = windowInsets.getInsets(
                            WindowInsetsCompat.Type.systemBars() |
                                    WindowInsetsCompat.Type.ime());

                    v.setPadding(insets.left, insets.top, insets.right, insets.bottom);
                    return WindowInsetsCompat.CONSUMED;
                }
        );
    }

    public static void setupToolbar(Toolbar toolbar, Activity activity, String title) {
        toolbar.setTitle(title);
        toolbar.setNavigationIcon(android.R.drawable.ic_menu_close_clear_cancel);
        toolbar.setNavigationOnClickListener(v -> activity.finish());
    }
}

