package com.example.widgetsapp

import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.Log
import android.widget.RemoteViews

/**
 * Implementation of App Widget functionality.
 */
class MyWidget : AppWidgetProvider() {

    private var requestCode = 0

    override fun onUpdate(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetIds: IntArray
    ) {
        // There may be multiple widgets active, so update all of them
        for (appWidgetId in appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId, requestCode++)
        }
    }

    override fun onEnabled(context: Context) {
        // Enter relevant functionality for when the first widget is created
    }

    override fun onDisabled(context: Context) {
        // Enter relevant functionality for when the last widget is disabled
    }

    override fun onReceive(context: Context?, intent: Intent?) {
        super.onReceive(context, intent)
        val views = RemoteViews(context?.packageName, R.layout.my_widget)
        if(intent?.action == "com.example.widgetsApplication.Image1"){
            Log.i("widgetOnReceive", "Switched to image 1")
            views.setImageViewResource(R.id.imageView, R.drawable.image1)
        }
        if(intent?.action == "com.example.widgetsApplication.Image2"){
            Log.i("widgetOnReceive", "Switched to image 2")
            views.setImageViewResource(R.id.imageView, R.drawable.image2)
        }
        if(intent?.action == "com.example.widgetsApplication.Image3"){
            Log.i("widgetOnReceive", "Switched to image 3")
            views.setImageViewResource(R.id.imageView, R.drawable.image3)
        }
        if(context !=null)
            AppWidgetManager.getInstance(context).updateAppWidget(
                ComponentName( context, MyWidget::class.java), views
            )
    }
}

internal fun updateAppWidget(
    context: Context,
    appWidgetManager: AppWidgetManager,
    appWidgetId: Int,
    requestCode: Int
) {
    // Construct the RemoteViews object
    val views = RemoteViews(context.packageName, R.layout.my_widget)

    val intentPage = Intent(Intent.ACTION_VIEW)
    intentPage.data = Uri.parse("https://www.pja.edu.pl")
    val pendingIntentPage = PendingIntent.getActivity(
        context,
        requestCode,
        intentPage,
        PendingIntent.FLAG_MUTABLE
    )
    views.setOnClickPendingIntent(R.id.buttonPage, pendingIntentPage)

    val intentImage1 = Intent()
    intentImage1.action = "com.example.widgetsApplication.Image1"
    intentImage1.component = ComponentName(context, MyWidget::class.java)
    val pendingIntentImage1 = PendingIntent.getBroadcast(
        context,
        requestCode,
        intentImage1,
        PendingIntent.FLAG_MUTABLE
    )
    views.setOnClickPendingIntent(R.id.buttonImage1, pendingIntentImage1)

    val intentImage2 = Intent()
    intentImage2.action = "com.example.widgetsApplication.Image2"
    intentImage2.component = ComponentName(context, MyWidget::class.java)
    val pendingIntentImage2 = PendingIntent.getBroadcast(
        context,
        requestCode,
        intentImage2,
        PendingIntent.FLAG_MUTABLE
    )
    views.setOnClickPendingIntent(R.id.buttonImage2, pendingIntentImage2)

    val intentImage3 = Intent()
    intentImage3.action = "com.example.widgetsApplication.Image3"
    intentImage3.component = ComponentName(context, MyWidget::class.java)
    val pendingIntentImage3 = PendingIntent.getBroadcast(
        context,
        requestCode,
        intentImage3,
        PendingIntent.FLAG_MUTABLE
    )
    views.setOnClickPendingIntent(R.id.buttonImage3, pendingIntentImage3)



    // Instruct the widget manager to update the widget
    appWidgetManager.updateAppWidget(appWidgetId, views)
}