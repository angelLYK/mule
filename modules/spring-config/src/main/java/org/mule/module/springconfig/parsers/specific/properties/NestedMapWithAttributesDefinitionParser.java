/*
 * Copyright (c) MuleSoft, Inc.  All rights reserved.  http://www.mulesoft.com
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */
package org.mule.module.springconfig.parsers.specific.properties;

import org.mule.module.springconfig.parsers.AbstractMuleBeanDefinitionParser;
import org.mule.module.springconfig.parsers.assembly.MapEntryCombiner;
import org.mule.module.springconfig.parsers.collection.ChildSingletonMapDefinitionParser;
import org.mule.module.springconfig.parsers.delegate.AbstractSingleParentFamilyDefinitionParser;
import org.mule.module.springconfig.parsers.generic.AttributePropertiesDefinitionParser;
import org.mule.module.springconfig.parsers.processors.AddAttribute;

/**
 * This generates a nested map (an element of the parent map, with the key "mapKey", which is a map itself)
 * and then adds any attributes as name/value pairs.  Embedded elements can then insert further entries using
 * {@link org.mule.module.springconfig.parsers.collection.ChildSingletonMapDefinitionParser} or, if the entry is
 * just a simple value, {@link SimplePropertyDefinitionParser}.
 * The target setter is {@link org.mule.module.springconfig.parsers.assembly.MapEntryCombiner#VALUE}.
 */
public class NestedMapWithAttributesDefinitionParser extends AbstractSingleParentFamilyDefinitionParser
{

    public NestedMapWithAttributesDefinitionParser(String mapSetter, String mapKey)
    {
        addDelegate(new ChildSingletonMapDefinitionParser(mapSetter))
                .addCollection(mapSetter)
                .setIgnoredDefault(true)
                .removeIgnored(MapEntryCombiner.KEY)
                .registerPreProcessor(new AddAttribute(MapEntryCombiner.KEY, mapKey));
        addChildDelegate(new AttributePropertiesDefinitionParser(MapEntryCombiner.VALUE))
                .addIgnored(AbstractMuleBeanDefinitionParser.ATTRIBUTE_NAME)
                .addIgnored(MapEntryCombiner.KEY);
    }

}
