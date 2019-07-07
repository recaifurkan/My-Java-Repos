/*   Copyright 2013 Rajesh Putta

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
 * 
 * 
 */
package com.byrfb.net.json.utils;

import java.util.List;

import com.byrfb.net.json.exceptions.JSONParsingException;

public class JSONUtility {

	
	public static void handleFailure(List<String> heirarchyList, StringBuilder key,String... strings) {
		
		StringBuilder sb=new StringBuilder();
		sb.append("@Key-Heirarchy::");
		
		for(String ele:heirarchyList)
		{
			sb.append(ele).append("/");
		}
		
		sb.append("\t@Key::");
		
		sb.append(key).append("\t");
		
		for(String string:strings)
		{
			sb.append(string);
		}
		
		throw new JSONParsingException(sb.toString());
	}	
}
