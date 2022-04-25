package br.com.vayu.projections;

import java.util.List;

public interface CategoryLoginPageProjection {

    String getName();
    String getCode();
    String getIconPath();
    List<String> getSubcategoriesNames();

}
