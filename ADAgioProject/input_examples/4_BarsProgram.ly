\version "2.16.2"
\score {
 <<
\new Staff{
s1
s1
s1
s1

\set Staff.midiMinimumVolume = #0
\set Staff.midiMaximumVolume = #1.0
\set Staff.midiInstrument = #"acoustic grand"
<< { a''2\mf } \\ { s2 cis'''4\mf } \\ { s2 e'''4\mf } \\ { s2. a''4\mf } \\ { s1 } \\ >>
<< { c'''2 } \\ { s2 f'''4 } \\ { s2 a'''4 } \\ { s2. e'''4 } \\ { s1 } \\ >>
<< { f'''2 } \\ { s2 bes'''4 } \\ { s2 d''''4 } \\ { s2. g'''4 } \\ { s1 } \\ >>

}
\new Staff{

\tempo 4=90
\clef treble
\time 4/4
\set Staff.midiMinimumVolume = #0
\set Staff.midiMaximumVolume = #1.0
\set Staff.midiInstrument = #"acoustic grand"
<< { a'2\mf } \\ { s2 cis''4\mf } \\ { s2 e''4\mf } \\ { s2. a'4\mf } \\ { s1 } \\ >>
<< { c''2 } \\ { s2 f''4 } \\ { s2 a''4 } \\ { s2. s4 } \\ { s1 } \\ >>
<< { f''2 } \\ { s2 bes''4 } \\ { s2 d'''4 } \\ { s2. g''4 } \\ { s1 } \\ >>
<< { a''2 } \\ { s2 cis'''4 } \\ { s2 e'''4 } \\ { s2. a''4 } \\ { s1 } \\ >>
s1
s1
s1

}
>> 
\layout{ }
\midi {
\context {
\Score 
tempoWholesPerMinute = #(ly:make-moment 72 2)
}
}
}