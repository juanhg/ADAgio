\version "2.16.2"
\score {
 <<
\new Staff{
s1
s1
s1
s1
s1
s1
s1

\set Staff.midiMinimumVolume = #0
\set Staff.midiMaximumVolume = #1.0
\set Staff.midiInstrument = #"acoustic grand"
<< { a1\mf } \\ { s2 c'4\mf } \\ { s2 e'4\mf } \\ { s2. s4 } \\ { s1 } \\ >>
<< { s1 } \\ { s2 cisis'4 } \\ { s2 eis'4 } \\ { s2. ais4 } \\ { s1 } \\ >>
<< { aes1 } \\ { s2 ces'4 } \\ { s2 ees'4 } \\ { s2. s4 } \\ { s1 } \\ >>

}
\new Staff{
s1
s1

\set Staff.midiMinimumVolume = #0
\set Staff.midiMaximumVolume = #0.7
\set Staff.midiInstrument = #"violin"
<< { b2\mf } \\ { s2 d'4\mf } \\ { s2 fis'4\mf } \\ { s2. bis4\mf } \\ { s1 } \\ >>
<< { bis2 } \\ { s2 s4 } \\ { s2 s4 } \\ { s2. s4 } \\ { s1 } \\ >>
s1
s1
s1
<< { a2 } \\ { s2 c'4 } \\ { s2 e'4 } \\ { s2. s4 } \\ { s1 } \\ >>
<< { s2 } \\ { s2 cisis'4 } \\ { s2 eis'4 } \\ { s2. ais4 } \\ { s1 } \\ >>
<< { aes2 } \\ { s2 ces'4 } \\ { s2 ees'4 } \\ { s2. s4 } \\ { s1 } \\ >>

}
\new Staff{
s1
s1
s1
s1
s1
s1
s1

\set Staff.midiMinimumVolume = #0
\set Staff.midiMaximumVolume = #1.0
\set Staff.midiInstrument = #"acoustic grand"
<< { a2\mf } \\ { s2 c'4\mf } \\ { s2 e'4\mf } \\ { s2. s4 } \\ { s1 } \\ >>
<< { s2 } \\ { s2 cisis'4 } \\ { s2 eis'4 } \\ { s2. ais4 } \\ { s1 } \\ >>
<< { aes2 } \\ { s2 ces'4 } \\ { s2 ees'4 } \\ { s2. s4 } \\ { s1 } \\ >>

}
\new Staff{

\tempo 4=90
\clef treble
\time 4/4
\set Staff.midiMinimumVolume = #0
\set Staff.midiMaximumVolume = #1.0
\set Staff.midiInstrument = #"acoustic grand"
<< { a2\mf } \\ { s2 cisis'4\mf } \\ { s2 eis'4\mf } \\ { s2. ais4\mf } \\ { s1 } \\ >>
<< { aes2 } \\ { s2 ces'4 } \\ { s2 ees'4 } \\ { s2. aes4 } \\ { s1 } \\ >>
s1
s1
<< { a2 } \\ { s2 c'4 } \\ { s2 e'4 } \\ { s2. a4 } \\ { s1 } \\ >>
<< { ais2 } \\ { s2 cis'4 } \\ { s2 eis'4 } \\ { s2. ais4 } \\ { s1 } \\ >>
<< { s2 } \\ { s2 s4 } \\ { s2 s4 } \\ { s2. s4 } \\ { s1 } \\ >>
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