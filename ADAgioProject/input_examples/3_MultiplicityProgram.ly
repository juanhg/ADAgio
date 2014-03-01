\version "2.16.2"
\score {
 <<
\new Staff{

\tempo 4=90
\clef treble
\time 4/4
\set Staff.midiMinimumVolume = #0
\set Staff.midiMaximumVolume = #1.0
\set Staff.midiInstrument = #"acoustic grand"
<< { a2\mf } \\ { s2 dis'4\mf } \\ { s2 fis'4\mf } \\ { s2. b4\mf } \\ { s1 } \\ >>

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