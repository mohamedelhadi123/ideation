package org.exoplatform.ideation.Util;

import org.exoplatform.commons.utils.ListAccess;
import org.exoplatform.ideation.storage.dao.jpa.JPAQueryListAccess;


public class ListUtil {
  public static <E> int getSize(ListAccess<E> list) {
    try {
      return list.getSize();
    } catch (Exception ex) {
      return 0;
    }
  }

  public static <E> E[] load(ListAccess<E> list, int start, int limit) {
    try {
      if (list instanceof JPAQueryListAccess) {
        return list.load(start, limit);
      } else {
        if (limit < 0) {
          start = 0;
          limit = list.getSize();
        }
        return list.load(start, limit);
      }
    } catch (Exception ex) {
      throw new RuntimeException(ex);
    }
  }
}
