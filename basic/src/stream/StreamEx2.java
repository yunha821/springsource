package stream;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamEx2 {
    public static void main(String[] args) {

        File[] files = { new File("file1.txt"), new File("file2.txt"), new File("file3.txt"),
                new File("file4.txt") };

        // 파일 이름 출력
        for (File file : files) {
            System.out.print(file.getName() + "\t");
        }

        // 파일명 추출 후 리스트에 담기
        List<String> flist = new ArrayList<>();
        for (File file : files) {
            flist.add(file.getName());
        }
        System.out.println(flist);

        Stream<File> stream = Stream.of(new File("file1.txt"), new File("file2.txt"), new File("file3.txt"),
                new File("file4.txt"));

        // map() : 스트림 요소에 저장된 값 중에서 원하는 필드만 추출하거나 특정 형태로 변환하는 경우 사용
        Stream<String> names = stream.map(f -> f.getName());
        names.forEach(name -> System.out.println(name));

        Stream<File> stream2 = Stream.of(new File("file1.txt"), new File("file2.txt"), new File("file3.txt"),
                new File("file4.txt"));
        // IllegalStateException: stream has already been operated upon or closed -> 스트림
        // 일회용이기 때문에
        List<String> list = stream2.map(f -> f.getName()).collect(Collectors.toList());
        System.out.println(list);

        // stream2.map(f -> f.getName())
        // .collect(Collectors.toList())
        // .forEach(name -> System.out.println(name));

        List<String> list2 = Arrays.asList("abc", "def", "melon", "text", "apple");

        // 대문자로 변경 후 새로운 리스트로 생성 출력
        List<String> uppList = new ArrayList<>();
        list2.forEach(s -> uppList.add(s.toUpperCase()));
        System.out.println(uppList);

        // 스트림 하는 경우
        // 스트림 변환 => 연산 => 출력
        list2.stream().map(s -> s.toUpperCase())
                .collect(Collectors.toList())
                .forEach(System.out::println);
    }

}
