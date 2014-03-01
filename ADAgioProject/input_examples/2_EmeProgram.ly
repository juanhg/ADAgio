\version "2.16.2"
\score {
 <<
\new Staff{

\tempo 4=65
\clef treble
\time 4/4
\set Staff.midiMinimumVolume = #0
\set Staff.midiMaximumVolume = #0.8
\set Staff.midiInstrument = #"acoustic grand"
<< { c''2\mf } \\ { s2 b'4\mf } \\ { s2 d''4\mf } \\ { s2. g'4\mf } \\ { s1 } \\ >>
<< { d'2 } \\ { s2 c'4 } \\ { s2 e'4 } \\ { s2. a4 } \\ { s1 } \\ >>
<< { f2 } \\ { s2 b4 } \\ { s2 d'4 } \\ { s2. a4 } \\ { s1 } \\ >>

\tempo 4=70
\set Staff.midiMinimumVolume = #0
\set Staff.midiMaximumVolume = #1.0
<< { f4\mf } \\ { s4 a4\mf } \\ { s2 g'2\mf } \\ { s1 } \\ >>
<< { e4 } \\ { s4 gis'4 } \\ { s2 e'2 } \\ { s1 } \\ >>
<< { f4 } \\ { s4 a4 } \\ { s2 g'2 } \\ { s1 } \\ >>
<< { f4 } \\ { s4 a4 } \\ { s2 d'2 } \\ { s1 } \\ >>

\tempo 4=65
\set Staff.midiMinimumVolume = #0
\set Staff.midiMaximumVolume = #0.8
<< { c''2\mf } \\ { s2 b'4\mf } \\ { s2. d''4\mf } \\ { s1 } \\ >>
<< { a'2 } \\ { s2 a'4 } \\ { s2. c''4 } \\ { s1 } \\ >>
<< { d'2 } \\ { s2 b'4 } \\ { s2. e''4 } \\ { s1 } \\ >>

\tempo 4=70
\set Staff.midiMinimumVolume = #0
\set Staff.midiMaximumVolume = #1.0
<< { f'4\mf } \\ { s4 a'4\mf } \\ { s2 g'2\mf } \\ { s1 } \\ >>
<< { e'4 } \\ { s4 gis'4 } \\ { s2 e''2 } \\ { s1 } \\ >>
<< { f'4 } \\ { s4 a'4 } \\ { s2 g'2 } \\ { s1 } \\ >>
<< { f4 } \\ { s4 a4 } \\ { s2 d'2 } \\ { s1 } \\ >>

}
\new Staff{
s1
s1
s1

\set Staff.midiMinimumVolume = #0
\set Staff.midiMaximumVolume = #0.5
\set Staff.midiInstrument = #"flute"
<< { f'1\mf } \\ { s2 c'4\mf } \\ { s2. c'4\mf } \\ { s1 } \\ >>
<< { e'1 } \\ { s2 a'4 } \\ { s2. a'4 } \\ { s1 } \\ >>
<< { f'1 } \\ { s2 c'4 } \\ { s2. g'4 } \\ { s1 } \\ >>
<< { f'1 } \\ { s2 g'4 } \\ { s2. g'4 } \\ { s1 } \\ >>
s1
s1
s1
<< { f'1 } \\ { s2 c'4 } \\ { s2. c'4 } \\ { s1 } \\ >>
<< { e'1 } \\ { s2 a'4 } \\ { s2. a'4 } \\ { s1 } \\ >>
<< { f'1 } \\ { s2 c'4 } \\ { s2. g'4 } \\ { s1 } \\ >>
<< { f'1 } \\ { s2 g'4 } \\ { s2. g'4 } \\ { s1 } \\ >>

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