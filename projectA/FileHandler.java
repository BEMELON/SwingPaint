package SwingPaint.projectA;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.*;
import java.util.ArrayList;
import java.util.Base64;

public class FileHandler {
    private final ArrayList<Object> objects;
    private String filePath;

    public FileHandler() {
        objects = new ArrayList<>();
        filePath = "";
    }

    public FileHandler(String filePath) {
        objects = new ArrayList<>();
        this.filePath = filePath;
    }

    /**
     * 저장할 object를 추가
     * @param e 저장될 object
     */
    public void addObject(Object e) {
        objects.add(e);
    }

    /**
     * 객체를 byte[]로 변경해주는 helper
     * @return object를 byte[]로 변경
     */
    private byte[] objectToByteArray() {
        try {
            byte[] serializedData;
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos;
            oos = new ObjectOutputStream(baos);
            for (Object e : objects) {
                oos.writeObject(e);
            }
            return baos.toByteArray();
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 파일 경로를 기반으로 파일에 read/write 실행
     * @param filePath 파일의 경로
     */
    private void saveFile(String filePath) {
        if(!filePath.contains("swa")) {
            filePath += ".swa";
        }

        File oFile = new File(filePath);
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(oFile));
            byte[] serializedData = objectToByteArray();
            writer.write(Base64.getEncoder().encodeToString(serializedData));
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Main에서 호출되는 파일을 저장하는 메소드
     * filepath가 없는 경우: 파일 Dialog를 통해 파일을 선택
     * filepath가 있는 경우: 해당 파일에 재저장
     */
    public void save(Boolean saveNewFile) {
        if (saveNewFile || needFileChooser()) {
            JFileChooser chooser = new JFileChooser();
            chooser.setFileFilter(new FileNameExtensionFilter("swa or swb file", "swa", "swb"));
            int code = chooser.showSaveDialog(chooser);
            if (code == JFileChooser.FILES_ONLY) {
                this.filePath = chooser.getSelectedFile().getAbsolutePath();
                saveFile(filePath);
            } else {
                System.out.println("[파일-저장] 무언가 이상한걸 선택하셨어...<" + code + ">");
            }
        } else {
            saveFile(filePath);
        }
    }

    /**
     * 파일을 새로 연경우, file을 선택해야함.
     * 반면 파일을 불러온 경우 불러온 파일에 추가 저장
     * @return 새로운 파일 || 다른 이름으로 저장 --> True, 기존의 파일 --> False
     */
    public boolean needFileChooser() {
        return filePath.equals("");
    }

    public FigureBox load() {
        JFileChooser chooser = new JFileChooser();
        chooser.setFileFilter(new FileNameExtensionFilter("swa or swb file", "swa", "swb"));
        int code = chooser.showOpenDialog(chooser);
        if(code == JFileChooser.FILES_ONLY) {
            this.filePath = chooser.getSelectedFile().getAbsolutePath();
            if(!filePath.contains("swa")) {
                System.out.println(".swa 파일이 아닌 다른 파일을 open");
            } else {
                try {
                    char[] buf = new char[65535];
                    File iFile = new File(filePath);
                    BufferedReader reader = new BufferedReader(new FileReader(iFile));
                    int result = reader.read(buf);
                    if(result >= 65535) {
                        // TODO if byte has more then 65535
                        System.out.println("65535 이상의 byte, 수정 예정, " + result);
                    } else {
                        byte[] serializedData = Base64.getDecoder().decode(new String(buf).trim());
                        ByteArrayInputStream bais = new ByteArrayInputStream(serializedData);
                        ObjectInputStream ois = new ObjectInputStream(bais);
                        return (FigureBox) ois.readObject();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } else {
            System.out.println("[파일-불러오기] 무언가 이상한걸 선택하셨어...<" + code + ">");
        }
        return null;
    }
}
