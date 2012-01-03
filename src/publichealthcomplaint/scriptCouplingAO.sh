 #!/bin/bash
	rm *.txt;
        for i in $( ls ); do
	    find $i -name "*.aj" | xargs more | grep -E publichealthcomplaint. | grep -v publichealthcomplaint.$i | sort -nk 2 > $i.txt
        done

