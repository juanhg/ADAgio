\version "2.16.2"
\score {
 <<
\new Staff{

\tempo 4=90
\clef treble
\time 4/4
\set midiMinimumVolume = #0
\set midiMaximumVolume = #1.0
\set Staff.midiInstrument = #"acoustic grand"
<a c' e'>2\mf <b dis' fis'>2 
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