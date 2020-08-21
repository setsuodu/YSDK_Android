using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class Hook : MonoBehaviour
{
    public void NativeCallback(string log)
    {
        Debug.Log("[NativeCallback]" + log);
    }

    public void Init()
    {
        Debug.Log("Click Init..");

        AndroidJavaClass jc = new AndroidJavaClass("com.unity3d.player.UnityPlayer"); //Manifest中指定的启动页
        AndroidJavaObject jo = jc.GetStatic<AndroidJavaObject>("currentActivity");
        jo.Call("Init");
    }

    public void Login()
    {
        Debug.Log("Click Login..");

        AndroidJavaClass jc = new AndroidJavaClass("com.unity3d.player.UnityPlayer");
        AndroidJavaObject jo = jc.GetStatic<AndroidJavaObject>("currentActivity");
        jo.Call("Login");
    }

    public void Logout()
    {
        Debug.Log("Click Logout..");

        AndroidJavaClass jc = new AndroidJavaClass("com.unity3d.player.UnityPlayer");
        AndroidJavaObject jo = jc.GetStatic<AndroidJavaObject>("currentActivity");
        jo.Call("Logout");
    }

    public void Pay()
    {
        Debug.Log("Click Pay..");

        AndroidJavaClass jc = new AndroidJavaClass("com.unity3d.player.UnityPlayer");
        AndroidJavaObject jo = jc.GetStatic<AndroidJavaObject>("currentActivity");
        jo.Call("Pay");
    }
}
