package com.tencent.shadow.test.cases.plugin_main;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import androidx.test.core.app.ApplicationProvider;

import com.tencent.shadow.test.lib.test_manager.TestManager;

import org.junit.Test;

public class PackageManagerTest extends PluginMainAppTest {

    @Override
    protected Intent getLaunchIntent() {
        Intent pluginIntent = new Intent();
        String packageName = ApplicationProvider.getApplicationContext().getPackageName();
        pluginIntent.setClassName(
                packageName,
                "com.tencent.shadow.test.plugin.general_cases.lib.usecases.packagemanager.TestPackageManagerActivity"
        );
        return pluginIntent;
    }

    @Test
    public void testGetApplicationInfoClassName() {
        matchTextWithViewTag("getApplicationInfo/className", "com.tencent.shadow.test.plugin.general_cases.lib.gallery.TestApplication");
    }

    @Test
    public void testGetApplicationInfoNativeLibraryDir() {
        matchSubstringWithViewTag("getApplicationInfo/nativeLibraryDir", TestManager.uuid + "_lib");
    }

    @Test
    public void testGetApplicationInfoMetaData() {
        matchTextWithViewTag("getApplicationInfo/metaData", "test_value");
    }

    @Test
    public void testQueryContentProvidersName() {
        matchTextWithViewTag("queryContentProviders/size", ">0");
    }

    @Test
    public void testResolveActivityByExplicitIntent() {
        matchTextWithViewTag("resolveActivity/explicit", "com.tencent.shadow.test.plugin.general_cases.lib.usecases.packagemanager.TestPackageManagerActivity");
    }

    @Test
    public void testGetServiceInfoName() {
        matchTextWithViewTag("getServiceInfo/name", "com.tencent.shadow.test.plugin.general_cases.lib.usecases.service.TestService");
    }

    @Test
    public void testGetServiceInfoPackageName() {
        matchTextWithViewTag("getServiceInfo/packageName", "com.tencent.shadow.test.hostapp");
    }

    @Test
    public void testGetPackageInfoVersionName() throws PackageManager.NameNotFoundException {
        Context applicationContext = ApplicationProvider.getApplicationContext();
        String packageName = applicationContext.getPackageName();
        PackageInfo packageInfo = applicationContext.getPackageManager().getPackageInfo(packageName, 0);
        matchTextWithViewTag("getPackageInfo/versionName", packageInfo.versionName);
    }

    @Test
    public void testGetPackageInfoVersionCode() throws PackageManager.NameNotFoundException {
        Context applicationContext = ApplicationProvider.getApplicationContext();
        String packageName = applicationContext.getPackageName();
        PackageInfo packageInfo = applicationContext.getPackageManager().getPackageInfo(packageName, 0);
        matchTextWithViewTag("getPackageInfo/versionCode", Integer.toString(packageInfo.versionCode));
    }

}
