# common

This project contains function definitions that are (hopefully) useful across all Clojure projects.  Currently, this consists of functions to read Java properties files and for handling unix time.

## Installation

Download from http://madeye.com/code/lib/clojure/common-0.1.0.jar

## Usage

As it is a library project, common can not be executed directly. See the function docs for information on how to use the supplied functions.

## Examples

    com.madeye.clojure.common.common=> (load-props "test.properties")

    {:some.other.property "Another test value", :some.property "A test value"}

    com.madeye.clojure.common.common=> (current-unix-time)

    1371192804

    com.madeye.clojure.common.common=> (to-unix-time (tm/now))

    1371192824

    com.madeye.clojure.common.common=> (from-unix-time 1261738182)

    #<DateTime 2009-12-25T10:49:42.000Z>

### Bugs

None identified so far

## License

Copyright © 2013 Madeye Software Ltd.

Distributed under the Eclipse Public License, the same as Clojure.
