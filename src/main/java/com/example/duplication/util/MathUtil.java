package com.example.duplication.util;

import java.util.List;

public class MathUtil {
    public static float[] meanPool(List<float[]> vectors) {
        int dim = vectors.get(0).length;
        float[] mean = new float[dim];
        for (float[] v : vectors) for (int i = 0; i < dim; i++) mean[i] += v[i];
        for (int i = 0; i < dim; i++) mean[i] /= vectors.size();
        return mean;
    }
    public static double cosine(float[] a, float[] b) {
        double dot = 0, na = 0, nb = 0;
        for (int i = 0; i < a.length; i++) { dot += a[i]*b[i]; na += a[i]*a[i]; nb += b[i]*b[i]; }
        return (na == 0 || nb == 0) ? 0 : dot / (Math.sqrt(na) * Math.sqrt(nb));
    }
}
