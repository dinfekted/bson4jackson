// Copyright 2010-2016 James Roper, Michel Kraemer
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//     http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package de.undercouch.bson4jackson.serializers;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import de.undercouch.bson4jackson.BsonGenerator;
import de.undercouch.bson4jackson.types.JavaScript;

/**
 * Serializer for JavaScript
 * @since 1.3
 * @author James Roper
 * @author Michel Kraemer
 */
public class BsonJavaScriptSerializer extends JsonSerializer<JavaScript> {
	@Override
	public void serialize(JavaScript value, JsonGenerator gen,
			SerializerProvider provider) throws IOException {
		if (value == null) {
			provider.defaultSerializeNull(gen);
		} else if (gen instanceof BsonGenerator) {
			BsonGenerator bgen = (BsonGenerator)gen;
			bgen.writeJavaScript(value, provider);
		} else {
			gen.writeStartObject();
			gen.writeStringField("$code", value.getCode());
			gen.writeObjectField("$scope", value.getScope());
			gen.writeEndObject();
		}
	}
}
