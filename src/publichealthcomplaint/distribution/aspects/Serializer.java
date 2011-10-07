package publichealthcomplaint.distribution.aspects;

import publichealthcomplaint.distribution.spec.prov.LocalIterator;
import publichealthcomplaint.datatypes.IDateDt;
public aspect Serializer {

    declare parents : LocalIterator+ || IDateDt implements java.io.Serializable;
}
