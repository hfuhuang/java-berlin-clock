Story: The Berlin Clock

Meta:
@scope interview

Narrative:
    As a clock enthusiast
    I want to tell the time using the Berlin Clock
    So that I can increase then number of ways that I can read the time

Scenario: Midnight
When the time is 00:00:00
Then the clock should look like
Y
OOOO
OOOO
OOOOOOOOOOO
OOOO

Scenario: Just after midnight
When the time is 00:00:01
Then the clock should look like
O
OOOO
OOOO
OOOOOOOOOOO
OOOO

Scenario: Just before midnight
When the time is 23:59:59
Then the clock should look like
O
RRRR
RRRO
YYRYYRYYRYY
YYYY

Scenario: Midnight at 24:00:00
When the time is 24:00:00
Then the clock should look like
Y
RRRR
RRRR
OOOOOOOOOOO
OOOO

Scenario: Just at noon 12 o'clock
When the time is 12:00:00
Then the clock should look like
Y
RROO
RROO
OOOOOOOOOOO
OOOO

Scenario: Middle of the afternoon
When the time is 13:17:01
Then the clock should look like
O
RROO
RRRO
YYROOOOOOOO
YYOO

Scenario: Midnight Wrong Time
When the time is 24:00:01
Then time format exception like
Text '24:00:01' could not be parsed

Scenario: Wrong Format too short
When the time is 23:00
Then time format exception like
Text '23:00' could not be parsed

Scenario: Wrong Format with miliseconds
When the time is 23:00:00.001
Then time format exception like
Text '23:00:00.001' could not be parsed

Scenario: defined with examples
When the time is <time>
Then the clock should look like <result>

Examples:
|time|result|
|12:00:00|Y;RROO;RROO;OOOOOOOOOOO;OOOO|
|12:00:01|O;RROO;RROO;OOOOOOOOOOO;OOOO|
|00:00:00|Y;OOOO;OOOO;OOOOOOOOOOO;OOOO|
|23:59:59|O;RRRR;RRRO;YYRYYRYYRYY;YYYY|
|16:50:06|Y;RRRO;ROOO;YYRYYRYYRYO;OOOO|
|11:37:01|O;RROO;ROOO;YYRYYRYOOOO;YYOO|