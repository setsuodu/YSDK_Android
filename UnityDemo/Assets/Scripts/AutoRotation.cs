﻿using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class AutoRotation : MonoBehaviour
{
    void Update()
    {
        transform.Rotate(Vector3.up);
    }
}
