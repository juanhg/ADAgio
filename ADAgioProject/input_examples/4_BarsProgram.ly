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
<a' cis'' e''>1\mf 
<c'' ees'' g''>4 <d'' f'' a''>4 s4 s4 
<f'' a'' c'''>2 <g'' bes'' d'''>2 
<a'' cis''' e'''>1 
<a'' cis''' e'''>1 
<c''' ees''' g'''>4 <d''' f''' a'''>4 <e''' gis''' b'''>4 s4 
<f''' a''' c''''>2 <g''' bes''' d''''>2 
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