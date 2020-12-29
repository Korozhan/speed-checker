package com.company.test.serialization;

import com.company.test.Range;

public interface RangeSerializer {

    String serialize(Range range);

    Range deserialize(String text);
}
