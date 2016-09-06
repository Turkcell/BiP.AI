package com.turkcell.bipai.helloworld.model.tes.data;

import java.util.List;

/**
 * 
 * TES servisine gönderilecek içeriğin listesini tutan model sınıftır. compostion list içerisindeki her
 * bir eleman, alıcıya gönderilecek olan bilgilerin tutulduğu Content modelidir.
 * Formatı:
<pre>   
...
    "composition":{ 
        "list":[ 
           { 
              ....
           },
           {
           ....
           }
        ]
    }
...
</pre>
 * @see <a href="http://www.bip.ai/documentations/tek-kullaniciya-mesaj-gonderimi/">Gönderilebilecek parametrelerin tam listesi</a>
 * @author BiP AI
 * 
 */
public class Composition {

	private List<Content> list;

	public List<Content> getList() {
		return list;
	}

	public void setList(List<Content> list) {
		this.list = list;
	}
	
}
